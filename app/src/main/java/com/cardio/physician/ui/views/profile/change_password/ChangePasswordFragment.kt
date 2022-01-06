package com.cardio.physician.ui.views.profile.change_password

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.cardio.physician.R
import com.cardio.physician.network.api.Constants
import com.cardio.physician.databinding.FragmentChangePasswordBinding
import com.cardio.physician.domain.common.model.ValidationModel
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.ui.common.base.fragment.BaseFragmentAuth
import com.cardio.physician.ui.common.utils.*
import com.cardio.physician.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangePasswordFragment : BaseFragmentAuth(R.layout.fragment_change_password),
    View.OnClickListener {
    private val binding by viewBinding(FragmentChangePasswordBinding::bind)
    private val viewModel: ChangePasswordViewModel by viewModels()
    private var oldPasswordVisible: Boolean = false
    private var newPasswordVisible: Boolean = false
    private var confirmPasswordVisible: Boolean = false

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.change_password), backBtnVisibility = true)
        setListener()
        setObservers()
        enableButtonClick(0.3f, false)
    }

    private fun setListener() {
        binding.edtOldPassword.addTextChangedListener(TextChangeWatcher(binding.oldPasswordContainer,
            binding.tvOldPasswordError))
        binding.edtNewPassword.addTextChangedListener(TextChangeWatcher(binding.newPasswordContainer,
            binding.tvNewPasswordError))
        binding.edtConfirmPassword.addTextChangedListener(TextChangeWatcher(binding.confirmPasswordContainer,
            binding.tvConfirmPasswordError))

        binding.imgOldPassword.setOnClickListener(this)
        binding.imgNewPassword.setOnClickListener(this)
        binding.imgConfirmPassword.setOnClickListener(this)
        binding.btnChangePassword.setOnClickListener(this)
        binding.ivPasswordnfo.setOnClickListener {
            showInfoAlertDialog(requireActivity(), getString(R.string.alert_password_title),getString(R.string.info_password),null)
        }
    }


    private fun setObservers() {
        viewModel.changePasswordResponse.observe(viewLifecycleOwner) {
            handleApiCallback(it)
        }
        viewModel.firebaseException.observe(viewLifecycleOwner) {
            handleApiCallback(it)
        }
        lifecycleScope.launch {
            viewModel.validationChannel.receiveAsFlow().collect {
                manageViewsForValidation(it)
            }
        }
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.imgOldPassword -> {
                showPasswordImage(binding.imgOldPassword, binding.edtOldPassword,
                    oldPasswordVisible)
                oldPasswordVisible = !oldPasswordVisible
            }

            binding.imgNewPassword -> {
                showPasswordImage(binding.imgNewPassword,
                    binding.edtNewPassword,
                    newPasswordVisible)
                newPasswordVisible = !newPasswordVisible
            }

            binding.imgConfirmPassword -> {
                showPasswordImage(binding.imgConfirmPassword,
                    binding.edtConfirmPassword,
                    confirmPasswordVisible)
                confirmPasswordVisible = !confirmPasswordVisible
            }

            binding.btnChangePassword -> {
                viewModel.validatePassword(binding.edtOldPassword.text.toString(),
                    binding.edtNewPassword.text.toString(),
                    binding.edtConfirmPassword.text.toString())
            }

            binding.headerView.backBtn -> {
                findNavController().popBackStack()
            }
        }
    }

    private fun showPasswordImage(
        imgNewPassword: AppCompatImageView,
        editText: AppCompatEditText,
        isPasswordVisible: Boolean,
    ) {
        if (!isPasswordVisible) {
            imgNewPassword.setImageResource(R.drawable.ic_show_password)
            editText.transformationMethod = null
        } else {
            imgNewPassword.setImageResource(R.drawable.ic_hide_password)
            editText.transformationMethod = PasswordTransformationMethod()
        }
        editText.setSelection(editText.text!!.length)
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.CHANGE_EMAIL -> {
                        showAlertDialog(requireActivity() as AppCompatActivity,
                            getString(R.string.success),
                            getString(R.string.password_change_successfully),
                            getString(R.string.ok),
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

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnChangePassword.isEnabled = clickable
        binding.btnChangePassword.alpha = alpha
    }

    inner class TextChangeWatcher(private var view: View, private val errorTxt: TextView) :
        TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            errorTxt.visibility = View.GONE
            view.setBackgroundResource(R.drawable.edt_rounded_corner)
            var textView: TextView? = null
            when (view) {
                binding.oldPasswordContainer -> textView = binding.txtOldPassword
                binding.newPasswordContainer -> textView = binding.txtNewPassword
                binding.confirmPasswordContainer -> textView = binding.txtConfirmPassword
            }
            customAnimationForTextInput(requireContext(), textView!!, s, before)
            enableSaveBtn()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun enableSaveBtn() {
        viewModel.validateFieldsToSetAlpha(
            isTextEmpty(binding.edtOldPassword.text.toString()),
            isTextEmpty(binding.edtNewPassword.text.toString()),
            isTextEmpty(binding.edtConfirmPassword.text.toString())
        )
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
            R.id.edtOldPassword -> {
                binding.oldPasswordContainer.setBackgroundResource(bgDrawable)
            }
            R.id.edtNewPassword -> {
                binding.newPasswordContainer.setBackgroundResource(bgDrawable)
            }
            R.id.edtConfirmPassword -> {
                binding.confirmPasswordContainer.setBackgroundResource(bgDrawable)
            }
            else -> editText.setBackgroundResource(bgDrawable)
        }
        tvValidator.visibility = tvValidatorVisibility
        tvValidator.text = message
    }
}