package com.cardio.physician.ui.views.auth.login

import android.app.Activity
import android.content.DialogInterface
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
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cardio.physician.R
import com.cardio.physician.data.remote.fitnesstracker.fitbit.api.entities.User
import com.cardio.physician.databinding.FragmentLoginBinding
import com.cardio.physician.domain.common.model.ValidationModel
import com.cardio.physician.network.NetworkHelper
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.Timer.Companion.API_TIMEOUT
import com.cardio.physician.ui.common.utils.Timer.Companion.OTP_EXPIRED
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import com.cardio.physician.ui.views.auth.login.LoginViewModel
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : BaseFragmentAuth(R.layout.fragment_login), View.OnClickListener {

    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private var isPasswordVisible: Boolean = false

    @Inject
    lateinit var networkHelper: NetworkHelper

    private var resultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val countryData =
                    result.data!!.getSerializableExtra(CountrySelectActivity.RESULT_COUNTRY_DATA) as CountryData?
                if (countryData != null && !TextUtils.isEmpty(countryData.countryISD))
                    binding.countryCode.text = countryData.countryISD
            }
        }

    private var resultGoogleSignIn: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
                viewModel.onGoogleSignIn(task)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        viewModel.initializePhoneAuthCallBack()
        initializeGoogleSign()
        enableButtonClick(0.3f, false)
        hideProgress()
    }

    override fun onResume() {
        super.onResume()
        hideProgress()
    }

    private fun initializeGoogleSign() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    private fun setListener() {
        binding.edtUserName.addTextChangedListener(TextChangeWatcher(binding.edtUserName,
            binding.tvEmailError))
        binding.edtPassword.addTextChangedListener(TextChangeWatcher(binding.edtPassword,
            binding.tvPasswordError))
        preventSpaceOnEditText(binding.edtUserName)
        binding.btnLogin.setOnClickListener(this)
        binding.btnGoogleContainer.setOnClickListener(this)
        binding.forgotPassword.setOnClickListener(this)
        binding.txtSignup.setOnClickListener(this)
        binding.countryCode.setOnClickListener(this)
        binding.imgShowPassword.setOnClickListener(this)
    }

    private fun manageCountryCodeVisibility(
        countryCodeVisibility: Int,
        passwordVisibility: Int,
        loginBtnText: String,
    ) {
        binding.apply {
            countryCode.visibility = countryCodeVisibility
            countryCodeSeprator.visibility = countryCodeVisibility
            passwordValContainer.visibility = passwordVisibility
            btnLogin.text = loginBtnText
        }
    }

    private fun setObservers() {
        viewModel.loginApiResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.firebaseException.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.phoneAuthenticationResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        lifecycleScope.launch {
            viewModel.validationChannel.receiveAsFlow().collect {
                manageViewsForValidation(it)
            }
        }
        viewModel.userDetailDocument.observe(viewLifecycleOwner, {
            if(it.data!=null) {
                if (it.data.get(FireStoreDocKey.USER_TYPE) == UserType.USER_TYPE_PHYSICIAN) {
                    showToast(requireContext(), getString(R.string.user_logged_in_successfully))
                    apiConstant?.let { it1 -> moveToNextScreen(it1) }
                } else {
                    showToast(requireContext(), getString(R.string.email_already_in_use))
                }
            }
        })
    }


    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> {
                val email = binding.edtUserName.text.toString()
                val password = binding.edtPassword.text.toString()
                lifecycleScope.launch {
                    viewModel.validateFields(email, password, binding.countryCode.text.toString())
                }
            }
            binding.btnGoogleContainer -> {
                if (networkHelper.isNetworkConnected()) {
                    signWithGoogle()
                } else customSnackBarFail(requireContext(),
                    binding.root,
                    getString(R.string.err_no_network_available))
            }
            binding.forgotPassword -> {
                baseViewModel.setDirection(LoginFragmentDirections.loginToForgotPassword())
            }
            binding.txtSignup -> {
                baseViewModel.setDirection(LoginFragmentDirections.loginToSignUp())
            }
            binding.countryCode -> {
                startActivityForCountryCode()
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
        }
    }

    private fun startActivityForCountryCode() {
        val intent = Intent(requireContext(), CountrySelectActivity::class.java)
        intent.putExtra(CountrySelectActivity.EXTRA_SELECTED_COUNTRY, CountryData("US"))
        resultLauncher.launch(intent)
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.GOOGLE_SIGNUP-> checkIsUserVerified(apiResponse.apiConstant)
                    Constants.LOGIN -> checkIsUserVerified(apiResponse.apiConstant)

                    Constants.VALIDATION -> {
                        if (isNumericValue(binding.edtUserName.text.toString())) startPhoneNumberVerification(
                            binding.countryCode.text.toString()
                                .plus(binding.edtUserName.text.toString())
                        ) else viewModel.signWithEmailAndPassword(binding.edtUserName.text.toString(),
                            binding.edtPassword.text.toString())
                    }

                    Constants.SEND_OTP -> {
                        baseViewModel.setDirection(LoginFragmentDirections.loginToPhoneVerification(
                            viewModel.createModelForPhoneVerification("", "",
                                binding.countryCode.text.toString(),
                                binding.edtUserName.text.toString(),
                                "", "", ""
                            ), ENUM.INT_2
                        ))
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
                    requireContext(),
                    binding.root,
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

    private fun signWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultGoogleSignIn.launch(signInIntent)
    }

    private var apiConstant: String ?= null

    private fun checkIsUserVerified(apiConstant: String) {
        this.apiConstant = apiConstant
        val user = viewModel.auth.currentUser
        user?.let {
            if (user.isEmailVerified) {
                viewModel.getUserDetail()
            } else showDialogEmailNotVerified(user)
        }
    }

    private fun showDialogEmailNotVerified(user: FirebaseUser?) {
        showAlertDialog(
            requireActivity() as AppCompatActivity,
            "", getString(R.string.verify_email), getString(R.string.ok),
            getString(R.string.cancel),
            btnTwoVisibility = false
        ) { _: String, dialog: DialogInterface ->
            user?.sendEmailVerification()
            dialog.dismiss()
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
        lifecycleScope.launch {
            delay(API_TIMEOUT)
            hideProgress()
        }
    }

    private fun moveToNextScreen(apiConstant: String) {
        if(apiConstant==Constants.GOOGLE_SIGNUP){
//            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToSyncHealthDataFragmentDuringSignUp())
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
            requireActivity().finish()
        }else{
            startActivity(Intent(requireContext(), DashboardActivity::class.java))
            requireActivity().finish()
        }
    }

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnLogin.isEnabled = clickable
        binding.btnLogin.alpha = alpha
    }

    inner class TextChangeWatcher(private var view: View, private val errorTxt: TextView) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            errorTxt.visibility = View.GONE
            var editText: EditText? = null
            when (view) {
                binding.edtUserName -> {
                    if (isNumericValue(s.toString())) {
                        manageCountryCodeVisibility(
                            View.VISIBLE, View.GONE,
                            getString(R.string.request_otp)
                        )
                        binding.edtUserName.limitLength(resources.getInteger(R.integer.int_12))
                    } else {
                        manageCountryCodeVisibility(View.GONE,
                            View.VISIBLE,
                            getString(R.string.sign_in))

                        binding.edtUserName.limitLength(resources.getInteger(R.integer.int_35))
                    }
                    customAnimationForTextInput(requireContext(), binding.tvEmailAddress,
                        s, before)
                    binding.phoneNumberContainer.setBackgroundResource(R.drawable.edt_rounded_corner)
                    editText = binding.edtUserName
                    //preventSpaceOnEditText(binding.edtUserName)
                }
                binding.edtPassword -> {
                    customAnimationForTextInput(requireContext(), binding.tvPassword, s, before)
                    binding.passwordContainer.setBackgroundResource(R.drawable.edt_rounded_corner)
                    //preventSpaceOnEditText(binding.edtPassword)
                    binding.edtPassword.limitLength(resources.getInteger(R.integer.int_20))
                    editText = binding.edtPassword
                }
            }

            val result: String = s.toString().replace(" ", "")
            if (!s.toString().equals(result,false)) {
                editText?.setText(result)
                editText?.setSelection(result.length)
            }
            viewModel.validateFieldsToSetAlpha(binding.edtUserName.text.toString(),
                binding.edtPassword.text.toString())
        }

        override fun afterTextChanged(p0: Editable?) {
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
        //val editText = binding.root.findViewById(validationModel.edtResourceId) as EditText
        val tvValidator = binding.root.findViewById(validationModel.tvResourceId) as TextView
        if (validationModel.edtResourceId == R.id.edtUserName) {
            binding.phoneNumberContainer.setBackgroundResource(bgDrawable)
        }

        /*  R.id.edtPassword -> {
              binding.passwordContainer.setBackgroundResource(bgDrawable)
          }
          else -> editText.setBackgroundResource(bgDrawable)*/
        tvValidator.visibility = tvValidatorVisibility
        tvValidator.text = message
    }

}