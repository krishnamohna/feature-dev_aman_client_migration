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
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentLoginBinding
import com.cardio.doctor.model.request.PhoneVerificationDetails
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.activity.dashboard.DashboardActivity
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.countrypicker.CountrySelectActivity
import com.countrypicker.bean.CountryData
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class LoginFragment : AppBaseFragment(R.layout.fragment_login), View.OnClickListener {
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel: LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
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


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        // Initialize Firebase Auth
        auth = Firebase.auth
        initializePhoneAuthCallBack()
        googleSignIn()
    }

    private fun googleSignIn() {
        // Configure Google Sign In
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
        viewModel.signUpApiResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogin -> {
                val email = binding.edtUserName.text.toString()
                val password = binding.edtPassword.text.toString()
                viewModel.validateFields(email,password,binding.countryCode.text.toString())
            }
            binding.btnGoogleSignIn -> {
                signWithEmailAndPassword()
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
                    Constants.LOGIN -> {
                        if (isNumericValue(binding.edtUserName.text.toString())) startPhoneNumberVerification(
                            binding.countryCode.text.toString()
                                .plus(binding.edtUserName.text.toString())
                        )
                        else signWithEmailAndPassword(
                            binding.edtUserName.text.toString(),
                            binding.edtPassword.text.toString()
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
                    requireContext(),
                    binding.root,
                    getString(apiResponse.resourceId!!)
                )
            }
            Status.ALPHA -> {
            }
        }
    }


    private fun signWithEmailAndPassword() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        // Firebase sign out
        auth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            //updateUI(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // updateUI(null)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        showProgress()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    Snackbar.make(
                        binding.root,
                        "User Logged In Successfully ${user?.displayName}",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    openDashboardActivity()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    val view = binding.root
                    Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }
                hideProgress()
            }
    }

    companion object {
        private const val TAG = "Doctor App"
        private const val RC_SIGN_IN = 9001
    }

    private fun signWithEmailAndPassword(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        showProgress()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                   checkIsUserVerified()
                } else {
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    viewModel.checkForMultiFactorFailure(task.exception!!)
                }
                hideProgress()
            }
    }

    private fun checkIsUserVerified() {
        val user = auth.currentUser
        user?.let {
            if(user.isEmailVerified) {
                updateUI(user)
                openDashboardActivity()
            }else{
                showAlertDialog(requireActivity() as AppCompatActivity,
                    "", getString(R.string.verify_email), getString(R.string.ok),
                    getString(R.string.cancel),
                    btnTwoVisibility = false
                ) { _: String, dialog: DialogInterface ->
                    user.sendEmailVerification()
                    dialog.dismiss()
                }
            }
        }
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

    private fun initializePhoneAuthCallBack() {
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                hideProgress()
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false
                hideProgress()
                if (e is FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    customSnackBarFail(requireContext(), binding.root, getString(R.string.invalid_mobile_number))
                } else if (e is FirebaseTooManyRequestsException) {
                    customSnackBarFail(requireContext(), binding.root, getString(R.string.quota_exceeded))
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                hideProgress()
                storedVerificationId = verificationId
                resendToken = token

                val phoneVerificationDetails = PhoneVerificationDetails(
                    binding.countryCode.text.toString().plus(binding.edtUserName.text.toString()),
                    "", "",
                    verificationId, token
                )
                baseViewModel.setDirection(LoginFragmentDirections.loginToPhoneVerification(
                    phoneVerificationDetails, ENUM.INT_2
                ))
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

    private fun openDashboardActivity() {
        startActivity(Intent(requireContext(), DashboardActivity::class.java))
        requireActivity().finish()
    }
}