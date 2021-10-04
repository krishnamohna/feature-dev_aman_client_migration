package com.cardio.doctor.ui.views.fragment.dashboard

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.databinding.FragmentDashboardBinding
import com.cardio.doctor.ui.common.base.fragment.AppBaseFragment
import com.cardio.doctor.ui.common.utils.customSnackBarFail
import com.cardio.doctor.ui.common.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : AppBaseFragment(R.layout.fragment_dashboard), View.OnClickListener {
    private val binding by viewBinding(FragmentDashboardBinding::bind)
    private val viewModel: DashboardViewModel by viewModels()
    //private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        //googleSignIn()
        binding.btnLogOut.setOnClickListener(this)
    }

    private fun setListener() {
        binding.btnDashboardOne.setOnClickListener(this)
        binding.btnDashboardTwo.setOnClickListener(this)
        binding.btnProfileMenu.setOnClickListener(this)
        binding.appButton.setOnClickListener {  }
    }

    private fun setObservers() {

    }


    override fun onClick(view: View?) {
        when (view) {
            binding.btnDashboardOne -> {
                customSnackBarFail(requireContext(), binding.root, getString(R.string.coming_soon))

            }
            binding.btnDashboardTwo -> {
                customSnackBarFail(requireContext(), binding.root, getString(R.string.coming_soon))
            }
            binding.btnProfileMenu -> {
                baseViewModel.setDirection(DashboardFragmentDirections.dashboardToProfileFragment())
            }
        }
    }
/*
    private fun setStartDestinationOfNavGraph(navId: Int) {

        val navHostFragmentView =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val graphInflater = navHostFragmentView.navController.navInflater
        val navGraph = graphInflater.inflate(R.navigation.graph_dashboard)
        val navController = navHostFragmentView.navController
        navGraph.startDestination = navId
        navController.graph = navGraph
    }*/

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