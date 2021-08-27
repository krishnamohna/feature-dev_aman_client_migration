package com.cardio.doctor.ui.fragment.login

import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentLoginBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.activity.dashboard.DashboardActivity
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.Timer.Companion.OTP_EXPIRED
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class LoginFragment : AppBaseFragment(R.layout.fragment_login), View.OnClickListener {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient
    private var isPasswordVisible: Boolean = false

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
                viewModel.getGoogleSignedAccount(task)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        viewModel.initializePhoneAuthCallBack()
        initializeGoogleSign()
    }

    private fun initializeGoogleSign() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
    }

    private fun setListener() {
        binding.btnLogin.setOnClickListener(this)
        binding.btnGoogleSignIn.setOnClickListener(this)
        binding.forgotPassword.setOnClickListener(this)
        binding.txtSignup.setOnClickListener(this)
        binding.countryCode.setOnClickListener(this)
        binding.imgShowPassword.setOnClickListener(this)

        binding.edtUserName.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (isNumericValue(s.toString())) manageCountryCodeVisibility(
                    View.VISIBLE, View.GONE,
                    getString(R.string.request_otp)
                )
                else manageCountryCodeVisibility(View.GONE, View.VISIBLE, getString(R.string.login))
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    private fun manageCountryCodeVisibility(
        countryCodeVisibility: Int,
        passwordVisibility: Int,
        loginBtnText: String
    ) {
        binding.countryCode.visibility = countryCodeVisibility
        binding.passwordContainer.visibility = passwordVisibility
        binding.btnLogin.text = loginBtnText
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
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> {
                val email = binding.edtUserName.text.toString()
                val password = binding.edtPassword.text.toString()
                viewModel.validateFields(email, password, binding.countryCode.text.toString())
            }
            binding.btnGoogleSignIn -> {
                signWithGoogle()
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
        intent.putExtra(CountrySelectActivity.EXTRA_SELECTED_COUNTRY, CountryData("IN"))
        resultLauncher.launch(intent)
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.LOGIN ->  checkIsUserVerified()

                    Constants.VALIDATION -> {
                        if (isNumericValue(binding.edtUserName.text.toString())) startPhoneNumberVerification(
                            binding.countryCode.text.toString()
                                .plus(binding.edtUserName.text.toString())
                        ) else viewModel.signWithEmailAndPassword(binding.edtUserName.text.toString(),
                            binding.edtPassword.text.toString())
                    }

                    Constants.SEND_OTP -> {
                        baseViewModel.setDirection(LoginFragmentDirections.loginToPhoneVerification(
                            viewModel.createModelForPhoneVerification("",
                                binding.countryCode.text.toString(),
                                binding.edtUserName.text.toString(),
                                "","",""
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
            }
        }
    }

    private fun signWithGoogle() {
        val signInIntent = googleSignInClient.signInIntent
        resultGoogleSignIn.launch(signInIntent)
    }

    private fun checkIsUserVerified() {
        val user = viewModel.auth.currentUser
        user?.let {
            if (user.isEmailVerified) {
                showToast(requireContext(), getString(R.string.user_logged_in_successfully))
                openDashboardActivity()
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
    }

    private fun openDashboardActivity() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
        requireActivity().finish()
    }
}