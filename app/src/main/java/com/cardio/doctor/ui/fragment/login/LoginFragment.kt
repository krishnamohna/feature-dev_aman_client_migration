package com.cardio.doctor.ui.fragment.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentLoginBinding
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AppBaseFragment(R.layout.fragment_login), View.OnClickListener{
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel : LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
    }

    private fun setListener() {
        binding.btnSignup.setOnClickListener(this)
    }

    private fun setObservers() {
        /*  viewModel.userAuthenticationApiResponse.observe(viewLifecycleOwner, {
              handleApiCallback(it)
          })*/
    }

    override fun onClick(view: View?) {
        when(view){
            binding.btnSignup ->{
                baseViewModel.setDirection(LoginFragmentDirections.loginToSignUp())
            }
        }
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when(apiResponse.status){
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {

                }
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
            Status.ALPHA -> { }
        }
    }

}