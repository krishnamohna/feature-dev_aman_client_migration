package com.cardio.doctor.ui.fragment.profile.profile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentEditProfileBinding
import com.cardio.doctor.model.ValidationModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class EditProfileFragment : AppBaseFragment(R.layout.fragment_edit_profile), View.OnClickListener {

    private val binding by viewBinding(FragmentEditProfileBinding::bind)
    private val viewModel: UserProfileViewModel by viewModels()
    private var birthDate: Date? = null

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var isGranted = true
            permissions.entries.forEach {
                if (it.value == false) {
                    isGranted = false
                    return@forEach
                }
            }

            if (isGranted)
                fetchImage()
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.edit_profile), backBtnVisibility = true,
            editProfile = true)
        setListener()
        setObservers()
        binding.userProfileViewModel = viewModel
        viewModel.getUserDetail()
    }

    private fun setListener() {
        binding.imgProfilePic.setOnClickListener(this)
        binding.edtDob.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)

        binding.edtFirstName.addTextChangedListener(TextChangeWatcher(binding.edtFirstName,
            binding.tvFirstNameError))
        binding.edtLastName.addTextChangedListener(TextChangeWatcher(binding.edtLastName,
            binding.txtLastName))
        binding.edtEmailId.addTextChangedListener(TextChangeWatcher(binding.edtEmailId,
            binding.txtEmailAddress))
        binding.edtPhoneNumber.addTextChangedListener(TextChangeWatcher(binding.edtPhoneNumber,
            binding.txtPhoneNumber))
        binding.edtHeight.addTextChangedListener(TextChangeWatcher(binding.edtHeight,
            binding.txtTitleHeight))
        binding.edtHeart.addTextChangedListener(TextChangeWatcher(binding.edtHeart,
            binding.tvHeartError))
    }

    private fun setObservers() {
        viewModel.firebaseException.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })

        viewModel.userDetailDocument.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.uploadUserProfilePic.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.getUserProfilePic.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.userGender.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.editProfileResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        lifecycleScope.launch {
            viewModel.validationChannel.receiveAsFlow().collect {
                manageViewsForValidation(it)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.imgProfilePic -> {
                checkPermission()
            }

            binding.edtDob -> {
                getBirthDatePicker(requireContext(), birthDate) { _, year, month, date ->
                    val dateString  = getDate(date).plus("-")
                        .plus(getMonthNumber(month).toString().plus("-"))
                        .plus(year)
                    birthDate = dateString.toDate()
                    binding.edtDob.setText(getStringFromDate(birthDate))
                }?.show()
            }

            binding.btnSave -> {
                lifecycleScope.launch {
                    viewModel.validateFields(
                        binding.edtFirstName.text.toString(),
                        binding.edtLastName.text.toString(),
                        binding.edtEmailId.text.toString(),
                        binding.countryCode.text.toString(),
                        binding.edtPhoneNumber.text.toString(),
                        binding.edtDob.text.toString(),
                        binding.edtHeight.text.toString(),
                        binding.edtHeart.text.toString()
                    )
                }
            }
        }
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.USER_DETAIL -> showUserDetailOnUI(apiResponse.data as DocumentSnapshot)

                    Constants.UPLOAD_PROFILE_PIC -> {
                        viewModel.firebaseUri = apiResponse.data as Uri
                    }

                    Constants.USER_PROFILE_PIC -> {
                        val storageReference = apiResponse.data as Uri
                        Glide.with(requireContext())
                            .load(storageReference)
                            .apply(RequestOptions().circleCrop())
                            .placeholder(R.drawable.ic_profile_placeholder)
                            .into(binding.imgProfilePic)
                    }

                    Constants.USER_GENDER -> {
                        val gender = apiResponse.data as String
                        binding.txtTitleGender.visibility =
                            if (gender.equals(getString(R.string.select_gender), true)) {
                                View.VISIBLE
                            } else View.GONE
                    }

                    Constants.EDIT_PROFILE ->{
                        customSnackBarFail(requireContext(),binding.root,apiResponse.data as String)
                        findNavController().popBackStack()
                    }
                }
            }
            Status.LOADING -> {
                showProgress()
            }
            Status.ERROR -> {
                hideProgress()
                customSnackBarFail(requireContext(), binding.root, apiResponse.message!!)
            }
            Status.RESOURCE -> {
                hideProgress()
                customSnackBarFail(
                    requireContext(), binding.root,
                    getString(apiResponse.resourceId!!)
                )
            }

            Status.ALPHA -> {
                if (getString(apiResponse.resourceId!!).equals(getString(R.string.alpha_true),
                        true)
                ) {
                    enableButtonClick(1.0f, true)
                } else enableButtonClick(0.3f, false)
            }
        }
    }

    private fun showUserDetailOnUI(documentReference: DocumentSnapshot?) {
        Log.d("TAG", "showUserDetailOnUI() called ${documentReference.toString()}")
        if (documentReference != null && documentReference.data != null) {
//        documentReference?.data?.let {
            val firstName = documentReference.data?.get(FireStoreDocKey.FIRST_NAME) as String?
            val lastName = documentReference.data?.get(FireStoreDocKey.LAST_NAME) as String?
            val email = documentReference.data?.get(FireStoreDocKey.EMAIL) as String?
            val countryCode = documentReference.data?.get(FireStoreDocKey.COUNTRY_CODE) as String?
            val phoneNumber = documentReference.data?.get(FireStoreDocKey.PHONE_NUMBER) as String?
            val gender = documentReference.data?.get(FireStoreDocKey.GENDER) as String?
            val dob = documentReference.data?.get(FireStoreDocKey.DOB) as String?
            val height = documentReference.data?.get(FireStoreDocKey.HEIGHT) as String?
            val heartRate = documentReference.data?.get(FireStoreDocKey.HEART_RATE) as String?
            val genderList = resources.getStringArray(R.array.gender_list).toList()
            val imageUrl = documentReference.data?.get(FireStoreDocKey.IMAGE_URL) as String?

            if(!imageUrl.isNullOrEmpty()){
                viewModel.firebaseUri = imageUrl.convertIntoUri()
            }
            if(dob.isNullOrEmpty()) birthDate = dob?.toDate()

            binding.edtFirstName.setText(firstName ?: "")
            binding.edtLastName.setText(lastName ?: "")
            binding.edtEmailId.setText(email ?: "")
            binding.countryCode.text = countryCode ?: ""
            binding.edtPhoneNumber.setText(phoneNumber ?: "")
            binding.txtTitleGender.visibility = if (!gender.isNullOrEmpty() && !gender.equals(
                    getString(R.string.select_gender),
                    false)
            ) {
                binding.spinnerCategory.setSelection(genderList.indexOf(gender))
                View.VISIBLE
            } else View.GONE

            binding.edtDob.setText(dob ?: "")
            binding.edtHeight.setText(height ?: "")
            binding.edtHeart.setText(heartRate ?: "")

            viewModel.getImageDownloadUrl(documentReference.data?.get(FireStoreDocKey.IMAGE_URL) as String)
        }
    }

    inner class TextChangeWatcher(private var view: View, private val errorTxt: TextView) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            errorTxt.visibility = View.GONE
            if (view == binding.edtPhoneNumber) {
                binding.phoneNumberContainer.setBackgroundResource(R.drawable.edt_rounded_corner)
            } else view.setBackgroundResource(R.drawable.edt_rounded_corner)
            var textView: TextView? = null
            when (view) {
                binding.edtFirstName -> textView = binding.txtFirstName
                binding.edtLastName -> textView = binding.txtLastName
                binding.edtEmailId -> textView = binding.txtEmailAddress
                binding.edtPhoneNumber -> textView = binding.txtPhoneNumber
                binding.edtHeight -> textView = binding.txtTitleHeight
                binding.edtDob -> textView = binding.dobTxtTitle
                binding.edtHeart -> textView = binding.txtTitleHeart
            }
            customAnimationForTextInput(requireContext(), textView!!, s, before)
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }


    private fun checkPermission() {
        if (isAdded) {
            requestMultiplePermissions.launch(
                arrayOf(Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
            )
        }
    }

    private fun fetchImage() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON)
            .start(requireContext(), this)
    }

    @SuppressLint("Assert")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                val fileName = getFileName(requireContext(), result.uri)!!
                Glide.with(requireContext())
                    .load(result.uri)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.ic_profile_placeholder)
                    .into(binding.imgProfilePic)

                viewModel.uploadProfileImage(result.uri, fileName)
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                customSnackBarFail(requireContext(), binding.root, result.error.message!!)
            }
        }
    }

    private fun manageViewsForValidation(validationModel: ValidationModel) {
        if (validationModel.status == Status.SUCCESS) {
            manageViewVisibility(validationModel, R.drawable.edt_rounded_corner, View.GONE, "")
        } else if (validationModel.status == Status.ERROR) {
            manageViewVisibility(validationModel, R.drawable.edt_rounded_corner_red, View.VISIBLE,
                validationModel.message)
        }
    }

    private fun manageViewVisibility(
        validationModel: ValidationModel, bgDrawable: Int,
        tvValidatorVisibility: Int, message: String,
    ) {
        val editText = binding.root.findViewById(validationModel.edtResourceId) as EditText
        val tvValidator = binding.root.findViewById(validationModel.tvResourceId) as TextView
        when (validationModel.edtResourceId) {
            R.id.edtPhoneNumber -> {
                binding.phoneNumberContainer.setBackgroundResource(bgDrawable)
            }
            else -> editText.setBackgroundResource(bgDrawable)
        }
        tvValidator.visibility = tvValidatorVisibility
        tvValidator.text = message
    }

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnSave.isEnabled = clickable
        binding.btnSave.alpha = alpha
    }
}