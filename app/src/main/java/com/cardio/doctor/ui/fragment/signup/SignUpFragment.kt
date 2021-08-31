package com.cardio.doctor.ui.fragment.signup

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentSignUpBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.Timer.Companion.OTP_EXPIRED
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.getFileName
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.theartofdev.edmodo.cropper.CropImage.*
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignUpFragment : AppBaseFragment(R.layout.fragment_sign_up), View.OnClickListener {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    // private var isPasswordVisible: Boolean = false

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

    private var resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val countryData =
                    result.data!!.getSerializableExtra(CountrySelectActivity.RESULT_COUNTRY_DATA) as CountryData?
                if (countryData != null && !TextUtils.isEmpty(countryData.countryISD))
                    binding.countryCode.text = countryData.countryISD
            }
        }

    private fun startActivityForCountryCode() {
        val intent = Intent(requireContext(), CountrySelectActivity::class.java)
        intent.putExtra(CountrySelectActivity.EXTRA_SELECTED_COUNTRY, CountryData("IN"))
        resultLauncher.launch(intent)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, "", backBtnVisibility = true)
        setListener()
        setObservers()
        enableButtonClick(0.3f, false)
        viewModel.initializePhoneAuthCallBack()
    }

    private fun setListener() {
        binding.btnSignup.setOnClickListener(this)
        // binding.txtLogin.setOnClickListener(this)
        binding.imgShowPassword.setOnClickListener(this)
        binding.countryCode.setOnClickListener(this)
        binding.imgProfilePic.setOnClickListener(this)
        binding.edtFirstName.addTextChangedListener(TextChangeWatcher())
        binding.edtEmailId.addTextChangedListener(TextChangeWatcher())
        binding.edtPassword.addTextChangedListener(TextChangeWatcher())
        binding.edtConfirmPassword.addTextChangedListener(TextChangeWatcher())
        binding.edtPhoneNumber.addTextChangedListener(TextChangeWatcher())

        binding.chkBoxAcceptPolicy.setOnCheckedChangeListener { _, isChecked ->
            validationFieldsForAlpha(isChecked)
        }
        /*   binding.edtPhoneNumber.setOnFocusChangeListener { _, hasFocus ->
               if (!hasFocus) {
                   viewModel.isPhoneNumberExist(
                       binding.countryCode.text.toString()
                           .plus(binding.edtPhoneNumber.text.toString())
                   )
               }
           }
           binding.edtEmailId.setOnFocusChangeListener { _, hasFocus ->
               if (!hasFocus) {
                   viewModel.isEmailAlreadyExist(binding.edtEmailId.text.toString())
               }
           }*/
    }

    private fun setObservers() {
        viewModel.phoneAuthenticationResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.signUpApiResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.firebaseException.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
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
        activity().setGuidelines(CropImageView.Guidelines.ON)
            .start(requireContext(), this)
    }

    @SuppressLint("Assert")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                viewModel.deviceUri = result.uri
                viewModel.fileName = getFileName(requireContext(), viewModel.deviceUri!!)
                Glide.with(requireContext())
                    .load(viewModel.deviceUri)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.ic_place_holder_profile)
                    .into(binding.imgProfilePic)

                viewModel.uploadProfileImage(viewModel.deviceUri, viewModel.fileName)
            } else if (resultCode == CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                customSnackBarFail(requireContext(), binding.root, result.error.message!!)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.imgProfilePic -> {
                checkPermission()
            }

            binding.btnSignup -> {
                viewModel.validateFields(
                    binding.edtFirstName.text.toString(), binding.edtPhoneNumber.text.toString(),
                    binding.countryCode.text.toString(), binding.edtEmailId.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }

            /* binding.imgShowPassword -> {
                 if (!isPasswordVisible) {
                     isPasswordVisible = true
                     binding.imgShowPassword.setImageResource(R.drawable.ic_show_password)
                     binding.edtPassword.transformationMethod = null
                 } else {
                     isPasswordVisible = false
                     binding.imgShowPassword.setImageResource(R.drawable.ic_hide_password)
                     binding.edtPassword.transformationMethod = PasswordTransformationMethod()
                 }
                 binding.edtPassword.setSelection(binding.edtPassword.text!!.length)
             }*/

            binding.countryCode -> {
                startActivityForCountryCode()
            }
        }
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.SIGNUP -> {
                        startPhoneNumberVerification(binding.countryCode.text.toString()
                            .plus(binding.edtPhoneNumber.text.toString()))
                    }
//                    Constants.PHONE_VERIFICATION -> {
//                        signInWithPhoneAuthCredential(apiResponse.data as PhoneAuthCredential)
//                    }
                    Constants.SEND_OTP -> {
                        /*viewModel.storeUserDetailInFireStore(binding.countryCode.text.toString().plus(
                            binding.edtPhoneNumber.text.toString()
                        ))*/
                        var imagePath = ""
                        viewModel.firebaseUri?.let { imagePath = it.toString() }
                        baseViewModel.setDirection(
                            SignUpFragmentDirections.signupToPhoneVerification(
                                viewModel.createModelForPhoneVerification(
                                    binding.edtFirstName.text.toString(),
                                    binding.edtPhoneNumber.text.toString(),
                                    binding.countryCode.text.toString(),
                                    binding.edtEmailId.text.toString(),
                                    binding.edtPassword.text.toString(), imagePath
                                ), ENUM.INT_1
                            )
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
                customSnackBarFail(requireContext(),
                    binding.root,
                    getString(apiResponse.resourceId!!))
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

    private fun startPhoneNumberVerification(phoneNumber: String) {
        showProgress()
        val options = PhoneAuthOptions.newBuilder(viewModel.auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(OTP_EXPIRED, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(viewModel.callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
        viewModel.verificationInProgress = true
    }

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnSignup.isEnabled = clickable
        binding.btnSignup.alpha = alpha
    }


    inner class TextChangeWatcher : TextWatcher {
        //private var job: Job? = null
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
           /* job = lifecycleScope.launch {
                delay(INPUT_DELAY)*/
                validationFieldsForAlpha(binding.chkBoxAcceptPolicy.isChecked)
           // }
        }

        override fun afterTextChanged(p0: Editable?) {
           // job?.cancel()
        }
    }

    private fun validationFieldsForAlpha(isChecked: Boolean) {
        viewModel.validateFieldsToSetAlpha(isChecked,
            binding.edtFirstName.text.toString(), binding.edtEmailId.text.toString(),
            binding.edtPassword.text.toString(), binding.edtConfirmPassword.text.toString(),
            binding.edtPhoneNumber.text.toString())
    }
}