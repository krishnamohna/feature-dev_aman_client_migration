package com.cardio.doctor.ui.fragment.signup

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.databinding.FragmentSignUpBinding
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.fragment.login.LoginFragment
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.showToast
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SignUpFragment : AppBaseFragment(R.layout.fragment_sign_up), View.OnClickListener {
    private val binding by viewBinding(FragmentSignUpBinding::bind)
    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private var isPasswordVisible: Boolean = false
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

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
        // Initialize Firebase Auth
        auth = Firebase.auth

        // Initialize phone auth callbacks
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                // This callback will be invoked in two situations:
                // 1 - Instant verification. In some cases the phone number can be instantly
                //     verified without needing to send or enter a verification code.
                // 2 - Auto-retrieval. On some devices Google Play services can automatically
                //     detect the incoming verification SMS and perform verification without
                //     user action.
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                hideProgress()
                // Update the UI and attempt sign in with the phone credential
                // updateUI(STATE_VERIFY_SUCCESS, credential)
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                // This callback is invoked in an invalid request for verification is made,
                // for instance if the the phone number format is not valid.
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false
                hideProgress()
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    customSnackBarFail(requireContext(), binding.root, "Invalid phone number.")
                } else if (e is FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    Snackbar.make(
                        view, "Quota exceeded.",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

                // Show a message and update the UI
                //  updateUI(STATE_VERIFY_FAILED)
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                hideProgress()
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                Log.d(TAG, "onCodeSent:$verificationId")

                // Save verification ID and resending token so we can use them later
                storedVerificationId = verificationId
                resendToken = token
                baseViewModel.setDirection(SignUpFragmentDirections.signupToPhoneVerification())

                // Update UI
                //updateUI(STATE_CODE_SENT)
            }
        }
    }

    private fun setListener() {
        binding.btnSignup.setOnClickListener(this)
        binding.txtLogin.setOnClickListener(this)
        binding.imgShowPassword.setOnClickListener(this)
        binding.countryCode.setOnClickListener(this)
    }

    private fun setObservers() {
        viewModel.signUpApiResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnSignup -> {
                val userName = binding.edtUserName.text.toString()
                val phoneNumber = binding.edtPhoneNumber.text.toString()
                val email = binding.edtEmailId.text.toString()
                val password = binding.edtPassword.text.toString()
                viewModel.validateFields(userName, phoneNumber, email, password)
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
                        startPhoneNumberVerification(
                            binding.countryCode.text.toString()
                                .plus(binding.edtPhoneNumber.text.toString())
                        )

                        //createAccount(binding.edtEmailId.text.toString(), binding.edtPassword.text.toString())
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

    private fun createAccount(email: String, password: String) {
        showProgress()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "createUserWithEmail:success")
                    val user = auth.currentUser
                    baseViewModel.setDirection(SignUpFragmentDirections.signupToPhoneVerification())
                    updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    /*Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)*/
                    checkForMultiFactorFailure(task.exception!!)
                }

                hideProgress()
            }
    }


    private fun checkForMultiFactorFailure(e: Exception) {
        // Multi-factor authentication with SMS is currently only available for
        // Google Cloud Identity Platform projects. For more information:
        // https://cloud.google.com/identity-platform/docs/android/mfa
        when (e) {
            is FirebaseAuthMultiFactorException -> {
                Log.w(TAG, "multiFactorFailure", e)
                val resolver = e.resolver
            }
            is FirebaseAuthInvalidCredentialsException -> {
                showToast(requireContext(), "Invalid Password")
            }
            is FirebaseAuthInvalidUserException -> {
                showToast(requireContext(), "Email not in use")
            }
            is FirebaseAuthUserCollisionException -> {
                showToast(requireContext(), "Email already in use")
            }
            else -> {
                showToast(requireContext(), "User not valid")
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            showToast(requireContext(), getString(R.string.user_logged_in_successfully))
        } else {
            showToast(requireContext(), getString(R.string.custom_auth_signin_status_failed))
        }
    }

    companion object {
        private const val TAG = "Doctor App"
        private const val RC_SIGN_IN = 9001
        private const val STATE_VERIFY_SUCCESS = 4
        private const val STATE_SIGNIN_SUCCESS = 6
    }

    private fun startPhoneNumberVerification(phoneNumber: String) {
        showProgress()
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(phoneNumber)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(requireActivity())                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)

        verificationInProgress = true
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                hideProgress()
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")

                    val user = task.result?.user
                    baseViewModel.setDirection(SignUpFragmentDirections.signupToPhoneVerification())

                    //updateUI(STATE_SIGNIN_SUCCESS, user)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                        // binding.fieldVerificationCode.error = "Invalid code."
                    }
                    // Update UI
                    // updateUI(STATE_SIGNIN_FAILED)
                }
            }
    }

}