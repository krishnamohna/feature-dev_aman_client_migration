package com.cardio.doctor.ui.fragment.profile.sync_health_data

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.doctor.R
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentSyncHealthDataBinding
import com.cardio.doctor.utils.viewbinding.viewBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SyncHealthDataFragment : AppBaseFragment(R.layout.fragment_sync_health_data),
    View.OnClickListener {

    private val binding by viewBinding(FragmentSyncHealthDataBinding::bind)
    private val viewModel: SyncHealthViewModel by viewModels()
    private val listOfSyncingOption = arrayListOf<Boolean>()
    private lateinit var arrayOfImageView: Array<ImageView>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.sync_health_data), backBtnVisibility = true)
        arrayOfImageView = arrayOf(binding.imgFitBitDone, binding.imgGoogleFitDone)
        setListener()
        setObservers()
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
                resetListForSelection()
                listOfSyncingOption[0] = true
                arrayOfImageView[0].visibility = View.VISIBLE
                viewModel.storeSyncSelectionInPreference(getString(R.string.fitbit))
                findNavController().popBackStack()
            }

            binding.googleFitContainer -> {
                resetListForSelection()
                listOfSyncingOption[1] = true
                arrayOfImageView[1].visibility = View.VISIBLE
                viewModel.storeSyncSelectionInPreference(getString(R.string.google_fit))
                findNavController().popBackStack()
            }
        }
    }

    private fun resetListForSelection() {
        listOfSyncingOption.forEachIndexed { position: Int, _: Boolean ->
            listOfSyncingOption[position] = false
            arrayOfImageView[position].visibility = View.GONE
        }
    }
}