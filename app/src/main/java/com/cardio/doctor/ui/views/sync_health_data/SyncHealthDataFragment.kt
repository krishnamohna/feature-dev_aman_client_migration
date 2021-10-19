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
import com.cardio.doctor.di.REPO_GOOGLE
import com.cardio.doctor.domain.fitness.FitnessRepositary
import com.cardio.doctor.domain.fitness.model.FitnessModel
import com.cardio.doctor.domain.fitness.model.HeartRateModel
import com.cardio.doctor.ui.common.base.fragment.BaseFragment
import com.cardio.doctor.ui.common.utils.extentions.customObserver
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
open class SyncHealthDataFragment : BaseFragment<FragmentSyncHealthDataBinding>(),
    View.OnClickListener, AuthenticationHandler {

    val TAG = "SyncHealthDataFragment"
    val viewModel: SyncHealthViewModel by viewModels()
    private val listOfSyncingOption = arrayListOf<Boolean>()
    private lateinit var arrayOfImageView: Array<ImageView>
    var isFitBitHeartRatefetched = false
    var isFitBitProfilefetched = false

    @Inject
    @Named(REPO_GOOGLE)
    lateinit var googlefitRepositary: FitnessRepositary

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
        googlefitRepositary.onActivityResult(requestCode, resultCode, data)
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
        setListener()
        setObservers()
    }

    override fun onAuthFinished(result: AuthenticationResult?) {
        result?.isSuccessful?.let {
            if (it) onFitBitAuthenticated()
        }
    }

    open fun setListener() {
        val selectedHealthKit = viewModel.getSelectedHealthKit()
        listOfSyncingOption.add(0, false)
        listOfSyncingOption.add(1, false)
        if (selectedHealthKit.equals(getString(R.string.fitbit), true)) {
            if (viewModel.isFitbitLoggedIn()) {
                listOfSyncingOption[0] = true
                arrayOfImageView[0].visibility = View.VISIBLE
                onFitBitAuthenticated()
            }
        } else if (selectedHealthKit.equals(getString(R.string.google_fit), true)) {
            if (viewModel.isGooglefitLoggedIn()) {
                listOfSyncingOption[1] = true
                arrayOfImageView[1].visibility = View.VISIBLE
                onGoogleAuthenticated()
            }
        }
     //   setVisibilityOfSelection()
        binding.fitbitContainer.setOnClickListener(this)
        binding.googleFitContainer.setOnClickListener(this)
    }

/*    private fun setVisibilityOfSelection() {
        listOfSyncingOption.forEachIndexed { position: Int, _: Boolean ->
            arrayOfImageView[position].visibility = View.GONE
        }
    }*/

    open fun setObservers() {
        viewModel.getUserFitbitData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    isFitBitProfilefetched = true
                    onFitbitProfileDataRecieved(it)
                }
            }, onError = {
                ::onError
                isFitBitProfilefetched = true
            })
        viewModel.getHeartRateFitbitLiveData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    isFitBitHeartRatefetched = true
                    onFitbitHeartRateDataRecieved(it)
                }
            }, onError = {
                ::onError
                isFitBitHeartRatefetched = true
            })
        viewModel.getUserGoogleFitLiveData().customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    isFitBitProfilefetched = true
                    onGoogleProfileDataRecieved(it)
                }
            }, onError = {
                ::onError
                isFitBitProfilefetched = true
            })
        viewModel.getHeartRateGoogleLiveData()
            .customObserver(this, onLoading = ::showProgress, onSuccess = {
                it?.let {
                    isFitBitHeartRatefetched = true
                    onGoogleHeartRateDataRecieved(it)
                }
            }, onError = {
                ::onError
                isFitBitHeartRatefetched = true
            })

    }

    open fun onGoogleHeartRateDataRecieved(it: HeartRateModel) {

    }

    open fun onGoogleProfileDataRecieved(it: FitnessModel) {
    }

    open fun isCompleteFitBitDataRecieved() = isFitBitHeartRatefetched && isFitBitProfilefetched

    open fun onFitbitHeartRateDataRecieved(it: HeartRateModel) {
        //do what you need to do here may be update user profile
    }

    open fun onFitbitProfileDataRecieved(fitnessModel: FitnessModel) {
        //do what you need to do here may be update user profile
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.fitbitContainer -> {
                if (!viewModel.isFitbitLoggedIn())
                    viewModel.connectWithFitbit(resultLauncher, requireContext())
                else
                    onFitBitAuthenticated()
            }

            binding.googleFitContainer -> {
                if (!viewModel.isGooglefitLoggedIn())
                    viewModel.connectWithGooglefit(this)
                else
                    onGoogleAuthenticated()
            }
        }
    }

    private fun onGoogleAuthenticated() {
        resetListForSelection()
        listOfSyncingOption[1] = true
        arrayOfImageView[1].visibility = View.VISIBLE
        viewModel.storeSyncSelectionInPreference(getString(R.string.google_fit))
        onGoogleSelection()
    }

    private fun onFitBitAuthenticated() {
        viewModel.storeSyncSelectionInPreference(getString(R.string.fitbit))
        resetListForSelection()
        listOfSyncingOption[0] = true
        arrayOfImageView[0].visibility = View.VISIBLE
        onFitbitSelection()
    }

    /*if instance was directly made of this class then following method will be called*/
    open fun onGoogleSelection() {
        findNavController().popBackStack()
    }

    /*if instance was directly made of this class then following method will be called*/
    open fun onFitbitSelection() {
        findNavController().popBackStack()
    }

    private fun resetListForSelection() {
        listOfSyncingOption.forEachIndexed { position: Int, _: Boolean ->
            listOfSyncingOption[position] = false
            arrayOfImageView[position].visibility = View.GONE
        }
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