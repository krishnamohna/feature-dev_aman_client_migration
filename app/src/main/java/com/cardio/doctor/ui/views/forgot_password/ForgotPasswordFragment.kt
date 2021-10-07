package com.cardio.doctor.ui.views.forgot_password

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.databinding.FragmentForgotPasswordBinding
import com.cardio.doctor.domain.common.model.ValidationModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.doctor.ui.common.utils.customAnimationForTextInput
import com.cardio.doctor.ui.common.utils.customSnackBarFail
import com.cardio.doctor.ui.common.utils.showAlertDialog
import com.cardio.doctor.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ForgotPasswordFragment : BaseFragmentAuth(R.layout.fragment_forgot_password),
    View.OnClickListener {
    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, "", backBtnVisibility = true)
        setListners()
        setObservers()
        enableButtonClick(0.3f, false)
        hideProgress()
    }

    override fun onResume() {
        super.onResume()
        hideProgress()
    }

    private fun setListners() {
        binding.edtEmailId.addTextChangedListener(TextChangeWatcher(binding.edtEmailId))
        binding.btnForgotPassword.setOnClickListener(this)

        /* binding.edtConfirmPassword.addTextChangedListener(EditTextWatcher(requireContext(),
             binding.tvConfirmPassword))*/
        /*  binding.edtConfirmPassword.error = "Invalid email"
          binding.edtConfirmPassword.background =
              ResourcesCompat.getDrawable(resources, R.drawable.edt_rounded_corner_red, null)*/
    }

    private fun setObservers() {
        viewModel.validationObserver.observe(viewLifecycleOwner, {
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
        editText.setBackgroundResource(bgDrawable)
        tvValidator.visibility = tvValidatorVisibility
        tvValidator.text = message
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnForgotPassword -> {
                lifecycleScope.launch {
                    viewModel.validateFields(binding.edtEmailId.text.toString())
                }
            }
        }
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.FORGOT_PASSWORD -> {
                        showAlertDialog(requireActivity() as AppCompatActivity,
                            "", apiResponse.data as String, getString(R.string.ok),
                            getString(R.string.cancel),
                            btnTwoVisibility = false
                        ) { _: String, dialog: DialogInterface ->
                            findNavController().popBackStack()
                            dialog.dismiss()
                        }
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

    inner class TextChangeWatcher(private var view: View) : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            when (view) {
                binding.edtEmailId ->{
                    if (binding.edtEmailId.text.toString().isEmpty()) enableButtonClick(0.3f, false)
                    else enableButtonClick(1.0f, true)
                    customAnimationForTextInput(requireContext(), binding.tvEmailAddress, s, before)
                    binding.edtEmailId.setBackgroundResource(R.drawable.edt_rounded_corner)
                    binding.tvEmailError.visibility = View.GONE
                }
            }

            val result: String = s.toString().replace(" ", "")
            if (!s.toString().equals(result,false)) {
                binding.edtEmailId.setText(result)
                binding.edtEmailId.setSelection(result.length)
            }
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnForgotPassword.isEnabled = clickable
        binding.btnForgotPassword.alpha = alpha
    }
}