package com.cardio.doctor.ui.fragment.signup

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentSignUpBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.Timer.Companion.OTP_EXPIRED
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.from
import com.cardio.doctor.utils.getFileName
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.theartofdev.edmodo.cropper.CropImage.*
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignUpFragment : AppBaseFragment(R.layout.fragment_sign_up), View.OnClickListener {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    private var isPasswordVisible: Boolean = false

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
        setListener()
        setObservers()
        viewModel.initializePhoneAuthCallBack()
    }

    private fun setListener() {
        binding.btnSignup.setOnClickListener(this)
        binding.txtLogin.setOnClickListener(this)
        binding.imgShowPassword.setOnClickListener(this)
        binding.countryCode.setOnClickListener(this)
        binding.appLogo.setOnClickListener(this)
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
                val bitmap = MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, viewModel.deviceUri )
                binding.appLogo.setImageBitmap(bitmap)
                viewModel.uploadProfileImage(viewModel.deviceUri ,viewModel.fileName )
            } else if (resultCode == CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                customSnackBarFail(requireContext(),binding.root ,result.error.message !!)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.appLogo -> {
                checkPermission()
            }

            binding.btnSignup -> {
                viewModel.validateFields(
                    binding.edtUserName.text.toString(), binding.edtPhoneNumber.text.toString(),
                    binding.countryCode.text.toString(), binding.edtEmailId.text.toString(),
                    binding.edtPassword.text.toString()
                )
            }
            binding.txtLogin -> {
                findNavController().popBackStack()
            }
            binding.imgShowPassword -> {
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
            }

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
                        var imagePath =""
                        viewModel.firebaseUri?.let { imagePath = it.toString() }
                        baseViewModel.setDirection(
                            SignUpFragmentDirections.signupToPhoneVerification(
                                viewModel.createModelForPhoneVerification(
                                    binding.edtUserName.text.toString(),
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
                customSnackBarFail(requireContext(), binding.root,getString(apiResponse.resourceId!!))
            }
            Status.ALPHA -> {
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

    /* private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
         viewModel.auth.signInWithCredential(credential)
             .addOnCompleteListener(requireActivity()) { _ ->
                 hideProgress()
             }.addOnFailureListener {
                 hideProgress()
                 customSnackBarFail(
                     requireContext(), binding.root, it.localizedMessage
                         ?: getString(R.string.getting_some_error)
                 )
             }
     }*/
}