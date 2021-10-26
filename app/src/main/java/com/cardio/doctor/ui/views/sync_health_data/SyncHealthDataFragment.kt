package com.cardio.doctor.ui.views.sync_health_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult
import com.cardio.doctor.data.remote.fitnesstracker.googlefit.GoogleFitManager
import com.cardio.doctor.databinding.FragmentSyncHealthDataBinding
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.ui.common.base.fragment.BaseFragment
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import com.cardio.doctor.ui.views.healthlogs.HealthLogsActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SyncHealthDataFragment : BaseFragment<FragmentSyncHealthDataBinding>(),
    AuthenticationHandler {

    protected var isFitSelected = false
    protected var isGoogleFitSelected = false
    val TAG = "SyncHealthDataFragment"
    val viewModel: SyncHealthViewModel by viewModels()
    private lateinit var arrayOfImageView: Array<ImageView>

    var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (!AuthenticationManager.onActivityResult(
                    AuthenticationManager.RESULT_CODE,
                    result.resultCode,
                    result.data,
                    this
                )
            ) {
                // Handle other activity results, if needed
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            AppCompatActivity.RESULT_OK -> {
                val postSignInAction = GoogleFitManager.FitActionRequestCode.values()[requestCode]
                postSignInAction.let {
                    binding.googleFitContainer.performClick()
                }
            }
            else -> oAuthErrorMsg(requestCode, resultCode)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSyncHealthDataBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.sync_health_data), backBtnVisibility = true)
        arrayOfImageView = arrayOf(binding.imgFitBitDone, binding.imgGoogleFitDone)
        setViews()
        setListener()
        setObservers()
    }

    private fun setViews() {
        val selectedHealthKit = viewModel.getSelectedHealthKit()
        if (selectedHealthKit.equals(getString(R.string.fitbit), true)) {
            if (viewModel.isFitbitLoggedIn()) {
                onFitBitAuthenticated()
            }
        } else if (selectedHealthKit.equals(getString(R.string.google_fit), true)) {
            if (viewModel.isGooglefitLoggedIn()) {
                onGoogleAuthenticated()
            }
        }
    }

    override fun onAuthFinished(result: AuthenticationResult?) {
        result?.isSuccessful?.let {
            if (it) onFitBitAuthenticated()
        }
    }

    open fun setListener() {
        binding.fitbitContainer.setOnClickListener {
            if (!viewModel.isFitbitLoggedIn())
                viewModel.connectWithFitbit(resultLauncher, requireContext())
            else {
                onFitBitAuthenticated()
                onFitbitSelection()
            }
        }
        binding.googleFitContainer.setOnClickListener {
            if (!viewModel.isGooglefitLoggedIn())
                viewModel.connectWithGooglefit(this)
            else {
                onGoogleAuthenticated()
                onGoogleSelection()
            }
        }
        binding.healthLogsContainer.setOnClickListener {
           HealthLogsActivity.start(requireActivity())
        }
    }

    open fun setObservers() {
        viewModel.getUserFitbitData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    onFitbitProfileDataRecieved(it)
                }
            }, onError = {
                ::onError
            })
        viewModel.getUserGoogleFitLiveData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    onGoogleProfileDataRecieved(it)
                }
            }, onError = {
                ::onError
            })
    }

    open fun onGoogleProfileDataRecieved(it: FitnessModel) {
        //do what you need to do here may be update user profile
    }

    open fun onFitbitProfileDataRecieved(fitnessModel: FitnessModel) {
        //do what you need to do here may be update user profile
    }


    private fun onGoogleAuthenticated() {
        isFitSelected = false
        isGoogleFitSelected = true
        arrayOfImageView[1].visibility = View.VISIBLE
        arrayOfImageView[0].visibility = View.GONE
        viewModel.storeSyncSelectionInPreference(getString(R.string.google_fit))
    }

    private fun onFitBitAuthenticated() {
        isFitSelected = true
        isGoogleFitSelected = false
        viewModel.storeSyncSelectionInPreference(getString(R.string.fitbit))
        arrayOfImageView[1].visibility = View.GONE
        arrayOfImageView[0].visibility = View.VISIBLE
    }

    /*if instance was directly made of this class then following method will be called*/
    open fun onGoogleSelection() {
        findNavController().popBackStack()
    }

    /*if instance was directly made of this class then following method will be called*/
    open fun onFitbitSelection() {
        findNavController().popBackStack()
    }

    private fun oAuthErrorMsg(requestCode: Int, resultCode: Int) {
        val message = """
            There was an error signing into Fit. Check the troubleshooting section of the README
            for potential issues.
            Request code was: $requestCode
            Result code was: $resultCode
        """.trimIndent()
        Log.e(TAG, message)
    }

    fun logout(activity: Activity) {
        TODO("Not yet implemented")
    }

}