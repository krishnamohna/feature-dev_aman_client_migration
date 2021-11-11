package com.cardio.physician.ui.views.change_email

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.text.method.PasswordTransformationMethod
import android.view.View
import android.widget.EditText
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.lifecycleScope
import com.cardio.physician.R
import com.cardio.physician.databinding.ActivityChangeEmailBinding
import com.cardio.physician.domain.common.model.ValidationModel
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.network.api.Constants
import com.cardio.physician.network.api.EXTRAS
import com.cardio.physician.ui.common.base.activity.BaseActivity
import com.cardio.physician.ui.common.base.fragment.toolbar.IToolbar
import com.cardio.physician.ui.common.base.fragment.toolbar.NoImp
import com.cardio.physician.ui.common.utils.customAnimationForTextInput
import com.cardio.physician.ui.common.utils.customSnackBarFail
import com.cardio.physician.ui.common.utils.isTextEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ChangeEmailActivity : BaseActivity(),
    View.OnClickListener {
    private val binding by viewBinding(ActivityChangeEmailBinding::inflate)
    private val viewModel: ChangeEmailViewModel by viewModels()
    private var oldPasswordVisible: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setUpToolbar(binding.root, getString(R.string.verify_password), backBtnVisibility = true)
        setListener()
        setObservers()
        enableButtonClick(0.3f, false)
    }

    private fun setListener() {
        binding.edtOldPassword.addTextChangedListener(TextChangeWatcher(binding.oldPasswordContainer,
            binding.tvOldPasswordError))
        binding.imgOldPassword.setOnClickListener(this)
        binding.btnChangePassword.setOnClickListener(this)
    }


    private fun setObservers() {
        viewModel.changeEmailResponse.observe(this) {
            handleApiCallback(it)
        }
        viewModel.firebaseException.observe(this) {
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

            binding.btnChangePassword -> {
                viewModel.validatePassword(binding.edtOldPassword.text.toString(),intent.getStringExtra(EXTRAS.NEW_EMAIL_ADDRESS))
            }

            binding.headerView.backBtn -> {
                onBackPressed()
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
                        /*showAlertDialog(this,
                            getString(R.string.success),
                            getString(R.string.email_change_successfully),
                            getString(R.string.ok),
                            getString(R.string.cancel),
                            btnTwoVisibility = false
                        ) { _: String, dialog: DialogInterface ->
                            setResult(RESULT_OK)
                            finish()
                            dialog.dismiss()
                        }*/
                        customSnackBarFail(
                            this,
                            binding.root,
                            getString(R.string.email_change_successfully)
                        )
                        setResult(RESULT_OK)
                        finish()
                    }
                }
            }
            Status.LOADING -> {
                showProgress()
            }
            Status.ERROR -> {
                hideProgress()
                customSnackBarFail(this, binding.root, apiResponse.message!!)
            }
            Status.RESOURCE -> {
                hideProgress()
                customSnackBarFail(this,
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
            customAnimationForTextInput(this@ChangeEmailActivity, textView!!, s, before)
            enableSaveBtn()
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun enableSaveBtn() {
        viewModel.validateFieldsToSetAlpha(
            isTextEmpty(binding.edtOldPassword.text.toString())
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