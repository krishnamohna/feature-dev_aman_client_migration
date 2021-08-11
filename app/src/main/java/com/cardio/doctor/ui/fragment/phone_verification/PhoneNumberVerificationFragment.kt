package com.cardio.doctor.ui.fragment.phone_verification

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import androidx.appcompat.widget.AppCompatEditText
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentPhoneNumberVerificationBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhoneNumberVerificationFragment : AppBaseFragment(R.layout.fragment_phone_number_verification),
    View.OnClickListener {
    private val binding by viewBinding(FragmentPhoneNumberVerificationBinding::bind)
    private val viewModel : PhoneVerificationViewModel by viewModels()
    private lateinit var arrayOfEditText : Array<AppCompatEditText>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arrayOfEditText = arrayOf(binding.otpEdtText1,
            binding.otpEdtText2,
            binding.otpEdtText3,
            binding.otpEdtText4,
            binding.otpEdtText5,
            binding.otpEdtText6)
        setListeners()
        setObserver()
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

                }
            }

            binding.btnConfirm -> {

            }

        }
    }


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

}