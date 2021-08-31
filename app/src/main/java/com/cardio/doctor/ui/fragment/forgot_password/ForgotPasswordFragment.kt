package com.cardio.doctor.ui.fragment.forgot_password

import android.content.DialogInterface
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentForgotPasswordBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.showAlertDialog
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForgotPasswordFragment : AppBaseFragment(R.layout.fragment_forgot_password),
    View.OnClickListener {
    private val binding by viewBinding(FragmentForgotPasswordBinding::bind)
    private val viewModel: ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, "", backBtnVisibility = true)
        setListners()
        setObservers()
        enableButtonClick(0.3f, false)
    }

    fun setListners() {
        binding.edtEmailId.addTextChangedListener(TextChangeWatcher())
        binding.btnForgotPassword.setOnClickListener(this)
    }

    private fun setObservers() {
        viewModel.validationObserver.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.firebaseException.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
    }

    override fun onClick(v: View?) {
        when (v) {
            binding.btnForgotPassword -> {
                viewModel.validateFields(binding.edtEmailId.text.toString())
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

    inner class TextChangeWatcher : TextWatcher {
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
            if (binding.edtEmailId.text.toString().isEmpty()) enableButtonClick(0.3f, false)
            else enableButtonClick(1.0f, true)
        }

        override fun afterTextChanged(p0: Editable?) {
        }
    }

    private fun enableButtonClick(alpha: Float, clickable: Boolean) {
        binding.btnForgotPassword.isEnabled = clickable
        binding.btnForgotPassword.alpha = alpha
    }
}