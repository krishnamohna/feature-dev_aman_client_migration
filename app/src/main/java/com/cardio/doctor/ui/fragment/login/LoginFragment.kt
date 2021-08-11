package com.cardio.doctor.ui.fragment.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentLoginBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.ui.activity.dashboard.DashboardActivity
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.showToast
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.*
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : AppBaseFragment(R.layout.fragment_login), View.OnClickListener{
    private val binding by viewBinding(FragmentLoginBinding::bind)
    private val viewModel : LoginViewModel by viewModels()
    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        googleSignIn()
    }

    private fun googleSignIn() {
        // Configure Google Sign In
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        // Initialize Firebase Auth
        auth = Firebase.auth
    }




    private fun setListener() {
        binding.btnLogin.setOnClickListener(this)
        binding.btnGoogleSignIn.setOnClickListener(this)
        binding.forgotPassword.setOnClickListener(this)
        binding.txtSignup.setOnClickListener(this)
    }

    private fun setObservers() {
          viewModel.signUpApiResponse.observe(viewLifecycleOwner, {
              handleApiCallback(it)
          })
    }

    override fun onClick(view: View?) {
        when(view){
            binding.btnLogin ->{
                val email = binding.txtUserName.text.toString()
                val password = binding.txtPassword.text.toString()
                viewModel.validateFields(email,password)
            }
            binding.btnGoogleSignIn ->{
                signIn()
            }
            binding.forgotPassword ->{
            }
            binding.txtSignup ->{
                baseViewModel.setDirection(LoginFragmentDirections.loginToSignUp())
            }
        }
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when(apiResponse.status){
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.LOGIN ->{
                        signIn(binding.txtUserName.text.toString(), binding.txtPassword.text.toString())
                    }
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


    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    private fun signOut() {
        // Firebase sign out
        auth.signOut()

        // Google sign out
        googleSignInClient.signOut().addOnCompleteListener(requireActivity()) {
            //updateUI(null)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d(TAG, "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e)
                // updateUI(null)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        showProgress()
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithCredential:success")
                    val user = auth.currentUser
                    Snackbar.make(binding.root, "User Logged In Successfully ${user?.displayName}", Snackbar.LENGTH_SHORT).show()
                    openDashboardActivity()
                    //updateUI(user)
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithCredential:failure", task.exception)
                    val view = binding.root
                    Snackbar.make(view, "Authentication Failed.", Snackbar.LENGTH_SHORT).show()
                    //updateUI(null)
                }

                hideProgress()
            }
    }
    companion object {
        private const val TAG = "Doctor App"
        private const val RC_SIGN_IN = 9001
    }

    private fun signIn(email: String, password: String) {
        Log.d(TAG, "signIn:$email")
        /*if (!validateForm()) {
            return
        }*/
        showProgress()
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(requireActivity()) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                    openDashboardActivity()
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    /*Toast.makeText(context, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()*/
                    //updateUI(null)
                    checkForMultiFactorFailure(task.exception!!)
                }

                /*if (!task.isSuccessful) {
                    showToast(requireContext(),getString(R.string.auth_failed))
                }*/
                hideProgress()
            }

    }

    private fun checkForMultiFactorFailure(e: Exception) {
        // Multi-factor authentication with SMS is currently only available for
        // Google Cloud Identity Platform projects. For more information:
        // https://cloud.google.com/identity-platform/docs/android/mfa
        when (e) {
            is FirebaseAuthMultiFactorException -> {
                Log.w(TAG, "multiFactorFailure", e)
                val resolver = e.resolver
            }
            is FirebaseAuthInvalidCredentialsException -> {
                showToast(requireContext(),"Invalid Password")
            }
            is FirebaseAuthInvalidUserException -> {
                showToast(requireContext(),"Email not in use")
            }
            is FirebaseAuthUserCollisionException -> {
                showToast(requireContext(),"Email already in use")
            }
            else ->{
                showToast(requireContext(),"User not valid")
            }
        }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            showToast(requireContext(),getString(R.string.user_logged_in_successfully) )
        } else {
            showToast(requireContext(),getString(R.string.custom_auth_signin_status_failed))
        }
    }

    private fun openDashboardActivity(){
        startActivity(Intent(requireContext(),DashboardActivity::class.java))
        requireActivity().finish()
    }
}