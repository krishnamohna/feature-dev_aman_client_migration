package com.cardio.doctor.ui.fragment.forgot_password

import android.content.DialogInterface
import android.os.Bundle
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
    private val viewModel : ForgotPasswordViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.forgot_password),backBtnVisibility = true)
        setListeners()
        setObservers()
    }

    fun setListeners(){
        binding.btnResetPassword.setOnClickListener(this)
    }

    private fun setObservers() {
        viewModel.validationObserver.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
    }

    override fun onClick(v: View?) {
        when(v){
            binding.btnResetPassword ->{
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

}