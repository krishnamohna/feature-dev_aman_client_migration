package com.cardio.doctor.ui.fragment.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentDashboardBinding
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : AppBaseFragment(R.layout.fragment_dashboard), View.OnClickListener {
    private val binding by viewBinding(FragmentDashboardBinding::bind)
    private val viewModel: DashboardViewModel by viewModels()
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        //googleSignIn()
        binding.btnLogOut.setOnClickListener(this)
    }

    private fun setListener() {

    }


    private fun setObservers() {
    }


    override fun onClick(view: View?) {
        when (view) {
            binding.btnLogOut->{
                //signOut()
            }
        }
    }

    /*private fun googleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireActivity(), gso)
    }


    private fun signOut() {
        // Firebase sign out
        viewModel.auth.signOut()
        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            //updateUI(null)
        }
        openLoginActivity()
    }

    private fun openLoginActivity() {
        startActivity(Intent(requireContext(), AuthenticateUserActivity::class.java))
        requireActivity().finish()
    }*/
}