package com.cardio.physician.ui.views.auth.phone_verification

import android.content.DialogInterface
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentPhoneNumberVerificationBinding
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.ENUM
import com.cardio.physician.ui.common.utils.Timer.Companion.COUNT_DOWN_INTERVAL
import com.cardio.physician.ui.common.utils.Timer.Companion.MINUTES
import com.cardio.physician.ui.common.utils.Timer.Companion.MINUTES_IN_MILIS
import com.cardio.physician.ui.common.utils.Timer.Companion.OTP_EXPIRED
import com.cardio.physician.ui.common.utils.Timer.Companion.OTP_EXPIRE_IN_MILISECONDS
import com.cardio.physician.ui.common.utils.Timer.Companion.OTP_TIME_FORMAT
import com.cardio.physician.ui.common.utils.customSnackBarFail
import com.cardio.physician.ui.common.utils.showAlertDialog
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class PhoneNumberVerificationFragment :
    BaseFragmentAuth(R.layout.fragment_phone_number_verification),
    View.OnClickListener {
    private val binding by viewBinding(FragmentPhoneNumberVerificationBinding::bind)
    private val viewModel: PhoneVerificationViewModel by viewModels()
    private lateinit var arrayOfEditText: Array<AppCompatEditText>
    private val navArgs by navArgs<PhoneNumberVerificationFragmentArgs>()
    private var countDownTimer: CountDownTimer? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, "", backBtnVisibility = true)
        arrayOfEditText = arrayOf(
            binding.otpEdtText1, binding.otpEdtText2, binding.otpEdtText3,
            binding.otpEdtText4, binding.otpEdtText5, binding.otpEdtText6
        )
        setListeners()
        setObserver()
        enableButtonClick(0.3f, false)
        setOtpTimer()
        viewModel.initializePhoneAuthCallBack()
        setNavArgsInFields()
    }

    override fun onResume() {
        super.onResume()
        hideProgress()
    }

    private fun setNavArgsInFields() {
        navArgs.phoneVerificationDetail?.let {
            viewModel.resendToken = it.token!!
            viewModel.storedVerificationId = navArgs.phoneVerificationDetail?.verificationId!!
            binding.txtPhoneNumber.text =
                navArgs.phoneVerificationDetail?.countryCode.plus(" ")
                    .plus(navArgs.phoneVerificationDetail?.phoneNumber)
        }
    }

    private fun setObserver() {
        viewModel.phoneAuthenticationResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.phoneVerificationResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.validateForgotPasswordResponse.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.firebaseException.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
    }

    private fun setListeners() {
        binding.txtResendOtp.setOnClickListener(this)
        binding.btnConfirm.setOnClickListener(this)
        binding.otpEdtText1.addTextChangedListener(TextChangeWatcher(binding.otpEdtText1))
        binding.otpEdtText2.addTextChangedListener(TextChangeWatcher(binding.otpEdtText2))
        binding.otpEdtText3.addTextChangedListener(TextChangeWatcher(binding.otpEdtText3))
        binding.otpEdtText4.addTextChangedListener(TextChangeWatcher(binding.otpEdtText4))
        binding.otpEdtText5.addTextChangedListener(TextChangeWatcher(binding.otpEdtText5))
        binding.otpEdtText6.addTextChangedListener(TextChangeWatcher(binding.otpEdtText6))

        binding.otpEdtText1.setOnKeyListener(
            GenericKeyEvent(
                binding.otpEdtText1, binding.otpEdtText1
            )
        )
        binding.otpEdtText2.setOnKeyListener(
            GenericKeyEvent(binding.otpEdtText2, binding.otpEdtText1)
        )
        binding.otpEdtText3.setOnKeyListener(
            GenericKeyEvent(
                binding.otpEdtText3,
                binding.otpEdtText2
            )
        )
        binding.otpEdtText4.setOnKeyListener(
            GenericKeyEvent(binding.otpEdtText4, binding.otpEdtText3)
        )
        binding.otpEdtText5.setOnKeyListener(
            GenericKeyEvent(
                binding.otpEdtText5,
                binding.otpEdtText4
            )
        )
        binding.otpEdtText6.setOnKeyListener(
            GenericKeyEvent(
                binding.otpEdtText6,
                binding.otpEdtText5
            )
        )
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.txtResendOtp -> {
                resendVerificationCode()
                removeInputTextOnResetOtp()
            }

            binding.btnConfirm -> {
                viewModel.verifyPhoneNumberWithCode(viewModel.storedVerificationId,
                    getOtpFromView())
            }
        }
    }

    inner class TextChangeWatcher(private var view: View) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            when (view) {
                binding.otpEdtText1 -> moveToNextView(
                    binding.otpEdtText1,
                    binding.otpEdtText2
                )
                binding.otpEdtText2 -> moveToNextView(
                    binding.otpEdtText2,
                    binding.otpEdtText3
                )
                binding.otpEdtText3 -> moveToNextView(
                    binding.otpEdtText3,
                    binding.otpEdtText4
                )
                binding.otpEdtText4 -> moveToNextView(
                    binding.otpEdtText4,
                    binding.otpEdtText5
                )
                binding.otpEdtText5 -> moveToNextView(
                    binding.otpEdtText5,
                    binding.otpEdtText6
                )
                binding.otpEdtText6 -> binding.otpEdtText6.imeOptions = EditorInfo.IME_ACTION_DONE
            }
            viewModel.validateFieldsToSetAlpha(getOtpFromView())
        }

        override fun afterTextChanged(p0: Editable?) {}
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

    private fun moveToNextView(
        selectedEditText: AppCompatEditText,
        nextEditText: AppCompatEditText,
    ) {
        if (!TextUtils.isEmpty(selectedEditText.text.toString())
            && selectedEditText.text.toString().length == ENUM.INT_1
        ) {
            nextEditText.requestFocus()
        }
    }

    private fun getOtpFromView(): String {
        var otp = ""
        for (view in arrayOfEditText) {
            if (!TextUtils.isEmpty(view.text.toString())) {
                otp = otp.plus(view.text.toString())
            } else break
        }
        return otp
    }


    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.UPDATE_EMAIL_AND_PASSWORD -> showVerificationEmail()

                    Constants.PHONE_VERIFICATION -> {
                        if (navArgs.isComingFrom == ENUM.INT_1) {
                            navArgs.phoneVerificationDetail?.let {
                                viewModel.updateEmailAndPassword(
                                    apiResponse.data as FirebaseUser,
                                    navArgs.phoneVerificationDetail,
                                )
                            }
                        } else moveToNextScreen()
                    }

                    Constants.SEND_OTP -> setOtpTimer()
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

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnConfirm.isEnabled = clickable
        binding.btnConfirm.alpha = alpha
    }

    private fun showVerificationEmail() {
        showAlertDialog(
            requireActivity() as AppCompatActivity,
            "", getString(R.string.verification_link), getString(R.string.ok),
            getString(R.string.cancel),
            btnTwoVisibility = false
        ) { _: String, dialog: DialogInterface ->
            //baseViewModel.setDirection(PhoneNumberVerificationFragmentDirections.phoneVerificationToLoginScreen())
            moveToNextScreen()
            dialog.dismiss()
        }
    }

    private fun resendVerificationCode() {
        showProgress()
        navArgs.phoneVerificationDetail?.let {
            val optionsBuilder = PhoneAuthOptions.newBuilder(viewModel.auth)
                .setPhoneNumber(navArgs.phoneVerificationDetail!!.countryCode.plus(navArgs.phoneVerificationDetail!!.phoneNumber)) // Phone number to verify
                .setTimeout(OTP_EXPIRED, TimeUnit.SECONDS) // Timeout and unit
                .setActivity(requireActivity())           // Activity (for callback binding)
                .setCallbacks(viewModel.callbacks)
            optionsBuilder.setForceResendingToken(viewModel.resendToken)
            PhoneAuthProvider.verifyPhoneNumber(optionsBuilder.build())
        }
    }

    private fun moveToNextScreen() {
       /* startActivity(Intent(requireContext(), DashboardActivity::class.java))
        requireActivity().finish()*/
        findNavController().navigate(PhoneNumberVerificationFragmentDirections.actionPhoneNumberVerificationFragmentToSyncHealthDataFragmentDuringSignUp())
    }

    private fun setOtpTimer() {
        lifecycleScope.launch {
            countDownTimer =
                object : CountDownTimer(OTP_EXPIRE_IN_MILISECONDS, COUNT_DOWN_INTERVAL) {
                    override fun onTick(millisUntilFinished: Long) {
                        val f: NumberFormat = DecimalFormat(OTP_TIME_FORMAT)
                        val min = millisUntilFinished / MINUTES_IN_MILIS % MINUTES
                        val sec = millisUntilFinished / COUNT_DOWN_INTERVAL % MINUTES
                        setTimerOnView(f.format(min).plus(":").plus(f.format(sec)),
                            View.VISIBLE,
                            false)
                    }

                    override fun onFinish() {
                        setTimerOnView("00:00", View.VISIBLE, true)
                    }
                }.start()
        }
    }

    private fun setTimerOnView(text: String, visibility: Int, enableBtn: Boolean) {
        binding.txtOtpTimer.text = text
        binding.txtOtpTimer.visibility = visibility
        if (enableBtn) enableResendBtn(1.0f, true)
        else enableResendBtn(0.3f, false)
    }

    private fun removeInputTextOnResetOtp() {
        for (view in arrayOfEditText) {
            view.setText("")
        }
    }

    override fun onDestroyView() {
        if (countDownTimer != null) {
            countDownTimer!!.cancel()
        }
        super.onDestroyView()
    }

    private fun enableResendBtn(alpha: Float, clickable: Boolean) {
        binding.txtResendOtp.isEnabled = clickable
        binding.txtResendOtp.alpha = alpha
    }
}