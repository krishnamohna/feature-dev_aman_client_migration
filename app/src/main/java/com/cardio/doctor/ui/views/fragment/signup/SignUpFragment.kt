package com.cardio.doctor.ui.views.fragment.signup

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cardio.doctor.R
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.common.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentSignUpBinding
import com.cardio.doctor.domain.common.model.ValidationModel
import com.cardio.doctor.network.NetworkHelper
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.utils.*
import com.cardio.doctor.ui.common.utils.Timer.Companion.OTP_EXPIRED
import com.cardio.doctor.ui.common.utils.viewbinding.viewBinding
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import com.theartofdev.edmodo.cropper.CropImage.*
import com.theartofdev.edmodo.cropper.CropImageView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : AppBaseFragment(R.layout.fragment_sign_up), View.OnClickListener {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    private var isPasswordVisible: Boolean = false
    private var isConfirmVisible: Boolean = false

    @Inject
    lateinit var networkHelper: NetworkHelper

    private val requestMultiplePermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            var isGranted = true
            permissions.entries.forEach {
                if (it.value == false) {
                    isGranted = false
                    return@forEach
                }
            }

            if (isGranted) fetchImage()
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
        hideProgress()
    }

    override fun onResume() {
        super.onResume()
        hideProgress()
    }

    private fun setListener() {
        binding.btnSignup.setOnClickListener(this)
        binding.txtSignin.setOnClickListener(this)
        binding.imgShowPassword.setOnClickListener(this)
        binding.imgConfirmShowPassword.setOnClickListener(this)
        binding.countryCode.setOnClickListener(this)
        binding.imgProfilePic.setOnClickListener(this)
        binding.tvTermCondition.setOnClickListener(this)
        binding.tvPrivacyPolicy.setOnClickListener(this)

        binding.edtFirstName.addTextChangedListener(TextChangeWatcher(binding.edtFirstName,
            binding.tvFirstNameError))
        binding.edtLastName.addTextChangedListener(TextChangeWatcher(binding.edtLastName,
            binding.tvLastName))
        binding.edtEmailId.addTextChangedListener(TextChangeWatcher(binding.edtEmailId,
            binding.tvEmailError))
        binding.edtPassword.addTextChangedListener(TextChangeWatcher(binding.passwordContainer,
            binding.tvPasswordError))
        binding.edtConfirmPassword.addTextChangedListener(TextChangeWatcher(binding.confirmPasswordContainer,
            binding.tvConfirmPasswordError))
        binding.edtPhoneNumber.addTextChangedListener(TextChangeWatcher(binding.phoneNumberContainer,
            binding.tvPhoneNoError))

        binding.chkBoxAcceptPolicy.setOnCheckedChangeListener { _, isChecked ->
            validationFieldsForAlpha(isChecked)
        }
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
        lifecycleScope.launch {
            viewModel.validationChannel.receiveAsFlow().collect {
                manageViewsForValidation(it)
            }
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
        activity().setGuidelines(CropImageView.Guidelines.ON)
            .start(requireContext(), this)
    }

    @SuppressLint("Assert")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = getActivityResult(data)
            if (resultCode == Activity.RESULT_OK) {
                viewModel.deviceUri = result.uri
                //viewModel.fileName = getFileName(requireContext(), viewModel.deviceUri!!)
                Glide.with(requireContext())
                    .load(viewModel.deviceUri)
                    .apply(RequestOptions().circleCrop())
                    .placeholder(R.drawable.ic_place_holder_profile)
                    .into(binding.imgProfilePic)

                //viewModel.uploadProfileImage(viewModel.deviceUri, viewModel.fileName)
            } else if (resultCode == CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                customSnackBarFail(requireContext(), binding.root, result.error.message!!)
            }
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.txtSignin -> {
                findNavController().popBackStack()
            }
            binding.imgProfilePic -> {
                checkPermission()
            }

            binding.btnSignup -> {
                lifecycleScope.launch {
                    viewModel.validateFields(
                        binding.edtFirstName.text.toString(),
                        binding.edtLastName.text.toString(),
                        binding.edtPhoneNumber.text.toString(),
                        binding.countryCode.text.toString(),
                        binding.edtEmailId.text.toString(),
                        binding.edtPassword.text.toString(),
                        binding.edtConfirmPassword.text.toString()
                    )
                }
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

            binding.imgConfirmShowPassword -> {
                if (!isConfirmVisible) {
                    isConfirmVisible = true
                    binding.imgConfirmShowPassword.setImageResource(R.drawable.ic_show_password)
                    binding.edtConfirmPassword.transformationMethod = null
                } else {
                    isConfirmVisible = false
                    binding.imgConfirmShowPassword.setImageResource(R.drawable.ic_hide_password)
                    binding.edtConfirmPassword.transformationMethod = PasswordTransformationMethod()
                }
                binding.edtConfirmPassword.setSelection(binding.edtConfirmPassword.text!!.length)

            }
            binding.countryCode -> {
                startActivityForCountryCode()
            }

            binding.tvPrivacyPolicy -> {
                openWebUrl(getString(R.string.privacy_policy), WEBURL.PRIVACY_POLICY)
            }
            binding.tvTermCondition -> {
                openWebUrl(getString(R.string.terms_and_conditions), WEBURL.TERMS_AND_CONDITION)
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
                        var imagePath = ""
                        viewModel.deviceUri?.let { imagePath = it.toString() }
                        baseViewModel.setDirection(
                            SignUpFragmentDirections.signupToPhoneVerification(
                                viewModel.createModelForPhoneVerification(
                                    binding.edtFirstName.text.toString(),
                                    binding.edtLastName.text.toString(),
                                    binding.countryCode.text.toString(),
                                    binding.edtPhoneNumber.text.toString(),
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
        if (viewModel.isNetworkConnected()) {
            lifecycleScope.launch {
                val options = PhoneAuthOptions.newBuilder(viewModel.auth)
                    .setPhoneNumber(phoneNumber)       // Phone number to verify
                    .setTimeout(OTP_EXPIRED, TimeUnit.SECONDS) // Timeout and unit
                    .setActivity(requireActivity())                 // Activity (for callback binding)
                    .setCallbacks(viewModel.callbacks)          // OnVerificationStateChangedCallbacks
                    .build()
                PhoneAuthProvider.verifyPhoneNumber(options)
                viewModel.verificationInProgress = true
            }
        }
    }

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnSignup.isEnabled = clickable
        binding.btnSignup.alpha = alpha
    }


    inner class TextChangeWatcher(private var view: View, private val errorTxt: TextView) :
        TextWatcher {
        //private var job: Job? = null
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            errorTxt.visibility = View.GONE
            view.setBackgroundResource(R.drawable.edt_rounded_corner)
            var textView: TextView? = null
            var editText: EditText? = null

            when (view) {
                binding.edtFirstName -> {
                    textView = binding.txtFirstName
                    editText = binding.edtFirstName
                }
                binding.edtLastName -> {
                    textView = binding.txtLastName
                    editText = binding.edtLastName
                }
                binding.edtEmailId -> {
                    textView = binding.txtEmailAddress
                    editText = binding.edtEmailId
                }
                binding.passwordContainer -> {
                    textView = binding.txtPassword
                    editText = binding.edtPassword
                }
                binding.confirmPasswordContainer -> {
                    textView = binding.txtConfirmPassword
                    editText = binding.edtConfirmPassword
                }
                binding.phoneNumberContainer -> {
                    textView = binding.txtPhoneNumber
                    editText = binding.edtPhoneNumber
                }
            }

            val result: String = s.toString().replace(" ", "")
            if (!s.toString().equals(result,false)) {
                editText?.setText(result)
                editText?.setSelection(result.length)
            }
            customAnimationForTextInput(requireContext(), textView!!, s, before)
            validationFieldsForAlpha(binding.chkBoxAcceptPolicy.isChecked)
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun validationFieldsForAlpha(isChecked: Boolean) {
        viewModel.validateFieldsToSetAlpha(isChecked,
            binding.edtFirstName.text.toString(), binding.edtEmailId.text.toString(),
            binding.edtPassword.text.toString(), binding.edtConfirmPassword.text.toString(),
            binding.edtPhoneNumber.text.toString())
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
            R.id.edtPassword -> {
                binding.passwordContainer.setBackgroundResource(bgDrawable)
            }
            R.id.edtConfirmPassword -> {
                binding.confirmPasswordContainer.setBackgroundResource(bgDrawable)
            }
            R.id.edtPhoneNumber -> {
                binding.phoneNumberContainer.setBackgroundResource(bgDrawable)
            }
            else -> editText.setBackgroundResource(bgDrawable)
        }
        tvValidator.visibility = tvValidatorVisibility
        tvValidator.text = message
    }

    private fun openWebUrl(toolbarTitle: String, webUrl: String) {
        if (networkHelper.isNetworkConnected()) {
            baseViewModel.setDirection(SignUpFragmentDirections.signupToWebView(
                toolbarTitle, webUrl
            ))
        } else customSnackBarFail(requireContext(),
            binding.root,
            getString(R.string.err_no_network_available))
    }
}