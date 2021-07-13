package com.cardio.doctor.ui.fragment.login

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentLoginBinding
import com.cardio.doctor.utils.AppBaseFragment
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AppBaseFragment(R.layout.fragment_login), View.OnClickListener{
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel : LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
    }

    private fun setListener() {
        binding.btnSignup.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when(view){
            binding.btnSignup ->{
                Log.d("TAG", "onClick() called with: view = $viewModel")
                baseViewModel.setDirection(LoginFragmentDirections.loginToSignUp())
            }
        }
    }
}