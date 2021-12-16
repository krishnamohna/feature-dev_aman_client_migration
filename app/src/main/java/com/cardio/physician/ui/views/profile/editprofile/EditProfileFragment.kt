package com.cardio.physician.ui.views.profile.editprofile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentEditProfileBinding
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.common.model.UserType
import com.cardio.physician.domain.common.model.ValidationModel
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.common.base.fragment.BaseFragment
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.extentions.loadImage
import com.cardio.physician.ui.views.change_email.ChangeEmailActivity
import com.google.firebase.firestore.DocumentSnapshot
import com.theartofdev.edmodo.cropper.CropImage
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class EditProfileFragment : BaseFragment<FragmentEditProfileBinding>(), View.OnClickListener {

    private var userType: String? = null
    private var selectedImageUri: Uri? = null
    private val viewModel: EditUserProfileViewModel by viewModels()
    private var birthDate: Date? = null
    private val navArgs by navArgs<EditProfileFragmentArgs>()

    var isEmailEdited: (email: String) -> Unit = {
        Intent(requireContext(), ChangeEmailActivity::class.java).apply {
            putExtra(com.cardio.physician.ui.common.utils.EXTRAS.NEW_EMAIL_ADDRESS, it)
        }.run {
            resultLauncher.launch(this)
        }
    }

    private var resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.btnSave.performClick()
            }
        }


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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.edit_profile), backBtnVisibility = true)
        setListener()
        setObservers()
        binding.userProfileViewModel = viewModel
        launchWithMinDelay {
            viewModel.getUserDetail()
        }
        setViewVisiblitiesForData(navArgs.extrasUserModel)
    }

    private fun setListener() {
        binding.imgProfilePic.setOnClickListener(this)
        binding.edtDob.setOnClickListener(this)
        binding.btnSave.setOnClickListener(this)

        binding.edtFirstName.addTextChangedListener(
            TextChangeWatcher(
                binding.edtFirstName,
                binding.tvFirstNameError
            )
        )
        binding.edtLastName.addTextChangedListener(
            TextChangeWatcher(
                binding.edtLastName,
                binding.txtLastName
            )
        )
        binding.edtEmailId.addTextChangedListener(
            TextChangeWatcher(
                binding.edtEmailId,
                binding.txtEmailAddress
            )
        )
        binding.edtPhoneNumber.addTextChangedListener(
            TextChangeWatcher(
                binding.edtPhoneNumber,
                binding.txtPhoneNumber
            )
        )
        binding.edtDob.addTextChangedListener(
            TextChangeWatcher(
                binding.edtDob,
                binding.dobTxtTitle
            )
        )
        binding.edtHeight.addTextChangedListener(
            TextChangeWatcher(
                binding.edtHeight,
                binding.txtTitleHeight
            )
        )
        binding.edtWeight.addTextChangedListener(
            TextChangeWatcher(
                binding.edtWeight,
                binding.tvWeightEditProfileError
            )
        )
        binding.txtResendVerificationLink.setOnClickListener(this)
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
        viewModel.live_data_send_email_verification.observe(viewLifecycleOwner, {
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
                    val dateString = getDate(date).plus("-")
                        .plus(getMonthNumber(month).toString().plus("-"))
                        .plus(year)
                    birthDate = dateString.datePickerStringToDate(DateFormat_.DATE_FORMAT_DD_MM_YYYY_DATE_PICKER)
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
                        binding.edtWeight.text.toString(),
                        selectedImageUri,
                        selectedImageUri?.let {
                            getFileName(requireContext(), it)
                        },
                        userType,
                        isEmailEdited
                    )
                }
            }
            binding.txtResendVerificationLink -> {
                viewModel.sendEmailVerificationLink()
            }
        }
    }


    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {

                when (apiResponse.apiConstant) {
                    Constants.USER_DETAIL -> {
                        hideProgress()
                        showUserDetailOnUI(apiResponse.data as DocumentSnapshot)
                    }

                    Constants.UPLOAD_PROFILE_PIC -> {
                        viewModel.firebaseUri = apiResponse.data as Uri
                    }

                    Constants.USER_PROFILE_PIC -> {
                        val storageReference = apiResponse.data as Uri
//                        Glide.with(requireContext())
//                            .load(storageReference)
//                            .apply(RequestOptions().circleCrop())
//                            .placeholder(R.drawable.ic_profile_placeholder)
//                            .into(binding.imgProfilePic)
                        binding.imgProfilePic.loadImage(storageReference, true, true)
                    }

                    Constants.USER_GENDER -> {
                        val gender = apiResponse.data as String
                        binding.txtTitleGender.visibility =
                            if (gender.equals(getString(R.string.select_gender), true)) {
                                GONE
                            } else View.VISIBLE
                    }

                    Constants.EDIT_PROFILE -> {
                        hideProgress()
                        customSnackBarFail(
                            requireContext(),
                            binding.root,
                            apiResponse.data as String
                        )
                        findNavController().popBackStack()
                    }

                    Constants.EMAIL_SEND_VERIFICATION -> {
                        hideProgress()
                        customSnackBarFail(
                            requireContext(),
                            binding.root,
                            apiResponse.data as String
                        )
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
                if (getString(apiResponse.resourceId!!).equals(
                        getString(R.string.alpha_true),
                        true
                    )
                ) {
                    enableButtonClick(1.0f, true)
                } else enableButtonClick(0.3f, false)
            }
        }
    }

    private fun showUserDetailOnUI(documentReference: DocumentSnapshot?) {
        if (documentReference != null && documentReference.data != null) {
            val firstName = documentReference.data?.get(FireStoreDocKey.FIRST_NAME) as String?
            val lastName = documentReference.data?.get(FireStoreDocKey.LAST_NAME) as String?
            val email = documentReference.data?.get(FireStoreDocKey.EMAIL) as String?
            val countryCode = documentReference.data?.get(FireStoreDocKey.COUNTRY_CODE) as String?
            val phoneNumber = documentReference.data?.get(FireStoreDocKey.PHONE_NUMBER) as String?
            val gender = documentReference.data?.get(FireStoreDocKey.GENDER) as String?
            val dob = documentReference.data?.get(FireStoreDocKey.DOB) as String?
            val height = documentReference.data?.get(FireStoreDocKey.HEIGHT) as String?
            val heartRate = documentReference.data?.get(FireStoreDocKey.WEIGHT) as String?
            val genderList = resources.getStringArray(R.array.gender_list).toList()
            val imageUrl = documentReference.data?.get(FireStoreDocKey.IMAGE_URL) as String?
            userType = documentReference.data?.get(FireStoreDocKey.SIGN_UP_TYPE) as String?
            if (!imageUrl.isNullOrBlank()) {
                viewModel.firebaseUri = imageUrl.convertIntoUri()
               // viewModel.getImageDownloadUrl(imageUrl)
                binding.imgProfilePic.loadImage(imageUrl,true,true)
            }
            try {
                if (!dob.isNullOrEmpty()) birthDate = dob.datePickerStringToDate(com.cardio.physician.ui.common.utils.Constants.DATE_FORMAT_DD_MMM_YYYY)
            } catch (ex: Exception) {

            }
            binding.edtFirstName.setText(firstName ?: "")
            binding.edtLastName.setText(lastName ?: "")
            binding.edtEmailId.setText(email ?: "")
            /*binding.txtTitleGender.visibility = */
            if (!gender.isNullOrEmpty() && !gender.equals(
                    getString(R.string.select_gender),
                    false
                )
            ) {
                binding.spinnerCategory.setSelection(genderList.indexOf(gender))
            }
            binding.edtDob.setText(dob ?: "")
            binding.edtHeight.setText(height ?: "")
            binding.edtWeight.setText(heartRate ?: "")
            userType?.let {
                binding.phoneNumberValContainer.visibility = if (it == UserType.GOOGLE.name) {
                    View.GONE
                } else {
                    View.VISIBLE
                }
            }
            phoneNumber?.let {
                if (!it.trim().isEmpty()) {
                    binding.phoneNumberContainer.visibility = View.VISIBLE
                    binding.countryCode.text = countryCode ?: ""
                    binding.edtPhoneNumber.setText(it ?: "")
                } else {
                    binding.phoneNumberContainer.visibility = View.GONE
                }
            }
            viewModel.isEmailVerified().let {
                if (it == false) {
                    binding.edtEmailId.isClickable = true
                    binding.edtEmailId.alpha = 1.0f
                    binding.edtEmailId.isFocusableInTouchMode = true
                    binding.txtResendVerificationLink.visibility = View.VISIBLE
                } else {
                    binding.txtResendVerificationLink.visibility = View.GONE
                }
            }
        }
    }

    inner class TextChangeWatcher(private var view: View, private val errorTxt: TextView) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            errorTxt.visibility = GONE
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
                binding.edtWeight -> textView = binding.txtTitleWeight
            }
            val result: String = s.toString().replace(" ", "")
            if (!s.toString().equals(result, false) && view != binding.edtDob) {
                (view as EditText).setText(result)
                (view as EditText).setSelection(result.length)
            }
            customAnimationForTextInput(requireContext(), textView!!, s, before)
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }


    private fun checkPermission() {
        if (isAdded) {
            requestMultiplePermissions.launch(
                arrayOf(
                    Manifest.permission.CAMERA,
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
                //lets not upload here. we will upload when user save changes
                //  viewModel.uploadProfileImage(result.uri, fileName)
                selectedImageUri = result.uri
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                customSnackBarFail(requireContext(), binding.root, result.error.message!!)
            }
        }
    }

    private fun manageViewsForValidation(validationModel: ValidationModel) {
        if (validationModel.status == Status.SUCCESS) {
            manageViewVisibility(validationModel, R.drawable.edt_rounded_corner, GONE, "")
        } else if (validationModel.status == Status.ERROR) {
            manageViewVisibility(
                validationModel, R.drawable.edt_rounded_corner_red, View.VISIBLE,
                validationModel.message
            )
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

    /*
    setting labels visibilities in advance to avoid flicker when data is set
     */
    private fun setViewVisiblitiesForData(extrasUserModel: UserModel?) {
        extrasUserModel?.run {
            firstName?.isNotEmpty()?.let { if (it) binding.txtFirstName.visibility = View.VISIBLE }
            lastName?.isNotEmpty()?.let { if (it) binding.txtLastName.visibility = View.VISIBLE }
            email?.isNotEmpty()?.let { if (it) binding.txtEmailAddress.visibility = View.VISIBLE }
            phoneNumber?.isNotEmpty()
                ?.let {
                    if (it) {
                        binding.txtPhoneNumber.visibility = View.VISIBLE
                        binding.phoneNumberContainer.visibility = View.VISIBLE
                    }
                }
            gender?.isNotEmpty()
                ?.let { if (it) {
                    try {
                        val genderList = resources.getStringArray(R.array.gender_list).toList()
                        binding.spinnerCategory.setSelection(genderList.indexOf(gender))
                    }catch (exp:java.lang.Exception){}
                } }
            dob?.isNotEmpty()
                ?.let { if (it) binding.dobTxtTitle.visibility = View.VISIBLE }
        }
    }


}