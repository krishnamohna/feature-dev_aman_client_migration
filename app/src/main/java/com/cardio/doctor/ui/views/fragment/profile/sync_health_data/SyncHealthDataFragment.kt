package com.cardio.doctor.ui.views.fragment.profile.sync_health_data

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationHandler
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationManager
import com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication.AuthenticationResult
import com.cardio.doctor.databinding.FragmentSyncHealthDataBinding
import com.cardio.doctor.ui.common.base.fragment.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class SyncHealthDataFragment : BaseFragment<FragmentSyncHealthDataBinding>(),
    View.OnClickListener, AuthenticationHandler {

    private val viewModel: SyncHealthViewModel by viewModels()
    private val listOfSyncingOption = arrayListOf<Boolean>()
    private lateinit var arrayOfImageView: Array<ImageView>

    var resultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            if (!AuthenticationManager.onActivityResult(0, result.resultCode, result.data, this)) {
                // Handle other activity results, if needed
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=FragmentSyncHealthDataBinding.inflate(inflater,container,false)
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
            if (it) onFitBitSelection()
        }
    }

    private fun setListener() {
        /*
        * We can use Recycler view instead to handle static selection
        * */
        val selectedHealthKit = viewModel.getSelectedHealthKit()
        listOfSyncingOption.add(0, false)
        listOfSyncingOption.add(1, false)
        setVisibilityOfSelection()

        if (selectedHealthKit.equals(getString(R.string.fitbit), true)) {
            listOfSyncingOption[0] = true
            arrayOfImageView[0].visibility = View.VISIBLE
        } else {
            listOfSyncingOption[1] = true
            arrayOfImageView[1].visibility = View.VISIBLE
        }

        binding.fitbitContainer.setOnClickListener(this)
        binding.googleFitContainer.setOnClickListener(this)
    }

    private fun setVisibilityOfSelection() {
        listOfSyncingOption.forEachIndexed { position: Int, _: Boolean ->
            arrayOfImageView[position].visibility = View.GONE
        }

    }

    private fun setObservers() {

    }

    override fun onClick(view: View?) {
        when (view) {
            binding.fitbitContainer -> {
                if (!viewModel.isFitbitLoggedIn())
                    viewModel.connectWithFitbit(resultLauncher, requireContext())
                else
                    onFitBitSelection()
            }

            binding.googleFitContainer -> {
                viewModel.connectWithGoogleFit()
                onGoogleFitSelection()
            }
        }
    }

    private fun onGoogleFitSelection() {
        resetListForSelection()
        listOfSyncingOption[1] = true
        arrayOfImageView[1].visibility = View.VISIBLE
        viewModel.storeSyncSelectionInPreference(getString(R.string.google_fit))
        onWearableSelection()
    }

    private fun onFitBitSelection() {
        viewModel.storeSyncSelectionInPreference(getString(R.string.fitbit))
        resetListForSelection()
        listOfSyncingOption[0] = true
        arrayOfImageView[0].visibility = View.VISIBLE
        onWearableSelection()
    }

    private fun onWearableSelection() {
        findNavController().popBackStack()
    }

    private fun resetListForSelection() {
        listOfSyncingOption.forEachIndexed { position: Int, _: Boolean ->
            listOfSyncingOption[position] = false
            arrayOfImageView[position].visibility = View.GONE
        }
    }


}