package com.cardio.doctor.ui.fragment.phone_verification

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentPhoneNumberVerificationBinding
import com.cardio.doctor.ui.activity.dashboard.DashboardActivity
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.showAlertDialog
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PhoneNumberVerificationFragment : AppBaseFragment(R.layout.fragment_phone_number_verification),
    View.OnClickListener {
    private val binding by viewBinding(FragmentPhoneNumberVerificationBinding::bind)
    private val viewModel : PhoneVerificationViewModel by viewModels()
    private lateinit var arrayOfEditText : Array<AppCompatEditText>

    private val navArgs by navArgs<PhoneNumberVerificationFragmentArgs>()
    private lateinit var auth: FirebaseAuth
    private lateinit var callbacks: PhoneAuthProvider.OnVerificationStateChangedCallbacks
    private var verificationInProgress = false
    private var storedVerificationId: String? = ""
    private var resendToken: PhoneAuthProvider.ForceResendingToken? =null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        arrayOfEditText = arrayOf(binding.otpEdtText1,
            binding.otpEdtText2,
            binding.otpEdtText3,
            binding.otpEdtText4,
            binding.otpEdtText5,
            binding.otpEdtText6)
        setListeners()
        setObserver()
        initializePhoneAuthCallBack()
        setNavArgsInFields()
    }

    private fun setNavArgsInFields() {
        navArgs.phoneVerificationDetail?.token?.let {
            resendToken = it
        }
        storedVerificationId = navArgs.phoneVerificationDetail?.verificationId
    }

    private fun setObserver() {


    }

    private fun setListeners(){
        binding.txtResendOtp.setOnClickListener(this)
        binding.btnConfirm.setOnClickListener(this)
        binding.otpEdtText1.addTextChangedListener(TextChangeWatcher(binding.otpEdtText1))
        binding.otpEdtText2.addTextChangedListener(TextChangeWatcher(binding.otpEdtText2))
        binding.otpEdtText3.addTextChangedListener(TextChangeWatcher(binding.otpEdtText3))
        binding.otpEdtText4.addTextChangedListener(TextChangeWatcher(binding.otpEdtText4))
        binding.otpEdtText5.addTextChangedListener(TextChangeWatcher(binding.otpEdtText5))
        binding.otpEdtText6.addTextChangedListener(TextChangeWatcher(binding.otpEdtText6))

        binding.otpEdtText1.setOnKeyListener(GenericKeyEvent(binding.otpEdtText1,binding.otpEdtText1))
        binding.otpEdtText2.setOnKeyListener(GenericKeyEvent(binding.otpEdtText2,binding.otpEdtText1))
        binding.otpEdtText3.setOnKeyListener(GenericKeyEvent(binding.otpEdtText3,binding.otpEdtText2))
        binding.otpEdtText4.setOnKeyListener(GenericKeyEvent(binding.otpEdtText4,binding.otpEdtText3))
        binding.otpEdtText5.setOnKeyListener(GenericKeyEvent(binding.otpEdtText5,binding.otpEdtText4))
        binding.otpEdtText6.setOnKeyListener(GenericKeyEvent(binding.otpEdtText6,binding.otpEdtText5))




    }
    override fun onClick(v: View?) {
        when(v){
            binding.txtResendOtp -> {
                if (binding.txtResendOtp.text.toString().equals(getString(R.string.resend), false)){
                    resendVerificationCode()
                }
            }

            binding.btnConfirm -> {
                verifyPhoneNumberWithCode(storedVerificationId, getOtpFromView())
            }
        }
    }

/*
    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when(apiResponse.status){
            Status.SUCCESS -> {
                hideProgress()

            }
            Status.LOADING -> {
                showProgress()
            }
            Status.ERROR -> {
                hideProgress()
                customSnackBarFail(requireContext(), binding.root, apiResponse.message !!)
            }
            Status.RESOURCE -> {
                hideProgress()
                customSnackBarFail(requireContext(),
                    binding.root,
                    getString(apiResponse.resourceId !!))
            }

            Status.ALPHA -> {
            }
        }
    }
*/


    inner class TextChangeWatcher(private var view: View) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
            Log.e("TAG", "beforeTextChanged")
        }
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            Log.e("TAG", "onTextChanged")
            when (view) {
                binding.otpEdtText1 -> moveToNextView(binding.otpEdtText1,
                    binding.otpEdtText2)
                binding.otpEdtText2 -> moveToNextView(binding.otpEdtText2,
                    binding.otpEdtText3)
                binding.otpEdtText3 -> moveToNextView(binding.otpEdtText3,
                    binding.otpEdtText4)
                binding.otpEdtText4 -> moveToNextView(binding.otpEdtText4,
                    binding.otpEdtText5)
                binding.otpEdtText5 -> moveToNextView(binding.otpEdtText5,
                    binding.otpEdtText6)
                binding.otpEdtText6 -> {
                    /*if (TextUtils.isEmpty(binding.otpEdtText6.text.toString())) {
                        binding.otpEdtText5.requestFocus()
                    }*/
                    binding.otpEdtText6.imeOptions = EditorInfo.IME_ACTION_DONE
                }
            }
            viewModel.validateFieldsToSetAlpha(getOtpFromView())
        }

        override fun afterTextChanged(p0: Editable?) {
            Log.e("TAG", "afterTextChanged")

        }
    }
    private class GenericKeyEvent(
        private val currentView: EditText,
        private val previousView: EditText?,
    ) :
        View.OnKeyListener {
        override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && currentView.text.toString()
                    .isEmpty()
            ) {
                previousView?.setText("")
                previousView?.requestFocus()
                return true
            }
            return false
        }
    }

    private fun moveToNextView(selectedEditText: AppCompatEditText,
                               nextEditText: AppCompatEditText
    ) {
        if(!TextUtils.isEmpty(selectedEditText.text.toString())
            && selectedEditText.text.toString().length == ENUM.INT_1 ) {
            nextEditText.requestFocus()
        }
    }
    private fun getOtpFromView() :String{
        var otp =""
        for (view in arrayOfEditText){
            if(!TextUtils.isEmpty(view.text.toString())){
                otp = otp.plus(view.text.toString())
            }else break
        }
        return otp
    }


    private fun verifyPhoneNumberWithCode(verificationId: String?, code: String) {
        val credential = PhoneAuthProvider.getCredential(verificationId!!, code)
        signInWithPhoneAuthCredential(credential)
    }

    private fun resendVerificationCode() {
        navArgs.phoneVerificationDetail?.let {
            val optionsBuilder = PhoneAuthOptions.newBuilder(auth)
                .setPhoneNumber(navArgs.phoneVerificationDetail!!.phoneNumber)       // Phone number to verify
                .setTimeout(120L, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(requireActivity())                 // Activity (for callback binding)
                .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            resendToken?.let {  optionsBuilder.setForceResendingToken(resendToken!!)}
            PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        showProgress()
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                hideProgress()
                if (task.isSuccessful) {
                    Log.d(TAG, "signInWithCredential:success")
                    val user = task.result?.user
                    if(navArgs.isComingFrom == ENUM.INT_1) {
                        updateEmailAndPassword(user)
                    } else startDashboardActivity()
                    //updateUI(STATE_SIGNIN_SUCCESS, user)
                } else {
                    // Sign in failed, display a message and update the UI
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        task.exception?.localizedMessage?.let {
                            customSnackBarFail(requireContext(),binding.root,
                                it
                            )
                        }
                        // The verification code entered was invalid
                        // binding.fieldVerificationCode.error = "Invalid code."
                    }
                    // Update UI
                    // updateUI(STATE_SIGNIN_FAILED)
                }
            }
    }


    private fun initializePhoneAuthCallBack() {
        // Initialize phone auth callbacks
        callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            override fun onVerificationCompleted(credential: PhoneAuthCredential) {
                Log.d(TAG, "onVerificationCompleted:$credential")
                verificationInProgress = false
                hideProgress()
                signInWithPhoneAuthCredential(credential)
            }

            override fun onVerificationFailed(e: FirebaseException) {
                Log.w(TAG, "onVerificationFailed", e)
                verificationInProgress = false
                hideProgress()
                if (e is FirebaseAuthInvalidCredentialsException) {
                    customSnackBarFail(requireContext(), binding.root, "Invalid phone number.")
                } else if (e is FirebaseTooManyRequestsException) {
                    customSnackBarFail(requireContext(),binding.root,"Quota exceeded.",
                        Snackbar.LENGTH_SHORT)
                }
            }

            override fun onCodeSent(
                verificationId: String,
                token: PhoneAuthProvider.ForceResendingToken
            ) {
                hideProgress()
                Log.d(TAG, "onCodeSent:$verificationId")
                storedVerificationId = verificationId
                resendToken = token
            }
        }
    }

    private fun updateEmailAndPassword(user: FirebaseUser?) {
        showProgress()
        user?.let {
            it.updateEmail(navArgs.phoneVerificationDetail?.email!!)
                .addOnCompleteListener { task ->
                    hideProgress()
                    if (task.isSuccessful) {
                        user!!.updatePassword(navArgs.phoneVerificationDetail?.password!!)
                            .addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    it.sendEmailVerification()

                                    Log.d(TAG, "updateEmailAndPassword() called with: task = ${it.email.plus("Is verified   : ${it.isEmailVerified}").plus("    ").plus(it.toString()) }")
                                    showAlertDialog(requireActivity() as AppCompatActivity,
                                        "", getString(R.string.verification_link), getString(R.string.ok),
                                        getString(R.string.cancel),
                                        btnTwoVisibility = false
                                    ) { _: String, dialog: DialogInterface ->
                                        startDashboardActivity()
                                        dialog.dismiss()
                                    }
                                }
                            }

                        Log.d(TAG, "User email address updated.")
                    }
                }
                .addOnFailureListener {
                    hideProgress()
                    customSnackBarFail(
                        requireContext(), binding.root, it.localizedMessage,
                        Snackbar.LENGTH_SHORT
                    )
                }
        }
    }

    private fun startDashboardActivity() {
        startActivity(Intent(requireContext(),DashboardActivity::class.java))
    }

    /*  private fun createAccount(email: String, password: String) {
          showProgress()

          auth.createUserWithEmailAndPassword(email, password)
              .addOnCompleteListener(requireActivity()) { task ->
                  if (task.isSuccessful) {
                      // Sign in success, update UI with the signed-in user's information
                      Log.d(SignUpFragment.TAG, "createUserWithEmail:success")
                      val user = auth.currentUser
                      baseViewModel.setDirection(SignUpFragmentDirections.signupToPhoneVerification())
                      updateUI(user)
                  } else {
                      // If sign in fails, display a message to the user.
                      Log.w(SignUpFragment.TAG, "createUserWithEmail:failure", task.exception)
                      *//*Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)*//*
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
                Log.w(SignUpFragment.TAG, "multiFactorFailure", e)
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
    }*/

    companion object {
        private const val TAG = "Doctor App"
    }
}