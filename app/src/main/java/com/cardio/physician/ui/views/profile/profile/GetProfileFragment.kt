package com.cardio.physician.ui.views.profile.profile

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cardio.physician.R
import com.cardio.physician.databinding.FragmentGetProfileBinding
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.network.Resource
import com.cardio.physician.network.Status
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.common.base.fragment.BaseToolBarFragment
import com.cardio.physician.ui.common.customviews.toolbar.base.IToolbar
import com.cardio.physician.ui.common.customviews.toolbar.ProfileToolbarImp
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.customSnackBarFail
import com.cardio.physician.ui.common.utils.extentions.loadImage
import com.cardio.physician.ui.common.utils.extentions.toUserModel
import com.cardio.physician.ui.common.utils.showToast
import com.cardio.physician.ui.views.dashboard.DashboardActivity
import com.cardio.physician.ui.views.diagnosis.DiagnosisActivity
import com.cardio.physician.ui.views.healthlogs.HealthLogsActivity
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetProfileFragment : BaseToolBarFragment<FragmentGetProfileBinding>(), View.OnClickListener {

    private var userType: String? = null
    private var userModel: UserModel? = null
    private val viewModel: UserProfileViewModel by viewModels()

    var resultHealthLogs: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            onBackButtonClick()
        }
    }

    private var resultDiagnosisReportSubmition: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                onBackButtonClick()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGetProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListener()
        setObservers()
        launchWithMinDelay {
            viewModel.getUserDetail()
        }
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as? DashboardActivity)?.registerDiagnosisClick {
            resultDiagnosisReportSubmition.launch(
                DiagnosisActivity.getActivityIntent(
                    requireActivity()
                )
            )
        }
    }

    override fun onPause() {
        super.onPause()
        (requireActivity() as? DashboardActivity)?.registerDiagnosisClick(null)
    }

    private fun setListener() {
        binding.wearableContainer.setOnClickListener(this)
        binding.settingContainer.setOnClickListener(this)
        binding.headerView.imgEditProfile.setOnClickListener(this)
    }

    private fun setObservers() {
        viewModel.firebaseException.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.userDetailDocument.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.getUserProfilePic.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        binding.healthLogsContainer.setOnClickListener {
            resultHealthLogs.launch(HealthLogsActivity.getIntent(requireActivity(), null))
        }
    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.USER_DETAIL -> {
                        if (apiResponse.data != null) {
                            userModel = (apiResponse.data as? DocumentSnapshot)?.toUserModel()
                            showUserDetailOnUI(apiResponse.data as DocumentSnapshot)
                        } else {
                            showToast(R.string.getting_some_error)
                        }
                    }

                    Constants.USER_PROFILE_PIC -> {
                        val storageReference = apiResponse.data as Uri
                        /*Glide.with(requireContext())
                            .load(storageReference)
                            .apply(RequestOptions().circleCrop())
                            .placeholder(R.drawable.ic_profile_placeholder)
                            .into(binding.imgProfilePic)*/
                        binding.imgProfilePic.loadImage(storageReference, true, true)
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
                    requireContext(), binding.root,
                    getString(apiResponse.resourceId!!)
                )
            }

            Status.ALPHA -> {
            }
        }
    }

    private fun showUserDetailOnUI(documentReference: DocumentSnapshot?) {
        if (documentReference != null && documentReference.data != null) {
            val firstName = documentReference.data?.get(FireStoreDocKey.FIRST_NAME) as String?
            val lastName = documentReference.data?.get(FireStoreDocKey.LAST_NAME) as String?
            val email = documentReference.data?.get(FireStoreDocKey.EMAIL) as String?
            val countryCode = documentReference.data?.get(FireStoreDocKey.COUNTRY_CODE) as String?
            val phoneNumber = documentReference.data?.get(FireStoreDocKey.PHONE_NUMBER) as String?
            val imageUrl = documentReference.data?.get(FireStoreDocKey.IMAGE_URL) as String?
            var gender = documentReference.data?.get(FireStoreDocKey.GENDER) as String?
            var weight = documentReference.data?.get(FireStoreDocKey.WEIGHT) as String?
            var height = documentReference.data?.get(FireStoreDocKey.HEIGHT) as String?
            userType = documentReference.data?.get(FireStoreDocKey.SIGN_UP_TYPE) as String?
            val userName = firstName?.plus(" ").plus(lastName ?: "")
            binding.txtUserName.text = userName
            binding.txtEmailAddress.text = email ?: ""
            phoneNumber?.let {
                if (!it.trim().isEmpty()) {
                    binding.txtPhoneNumber.visibility = View.VISIBLE
                    binding.txtPhoneNumber.text = "$countryCode  $phoneNumber"
                } else {
                    binding.txtPhoneNumber.visibility = View.INVISIBLE
                }
            }
            if (gender.isNullOrEmpty()) {
                gender = "NA"
            } else if (gender.equals(getString(R.string.select_gender), true)) {
                gender = "NA"
            }
            height = if (!height.isNullOrEmpty()) height else "NA"
            weight = if (!weight.isNullOrEmpty()) weight else "NA"

            binding.txtGender.text = gender
            binding.txtHeight.text = height
            binding.txtWeight.text = weight
            binding.txtSelectedWearable.text = viewModel.getSelectedHealthKit()
            if (!imageUrl.isNullOrBlank()) {
                // viewModel.getImageDownloadUrl(imageUrl)
                binding.imgProfilePic.loadImage(imageUrl, true, true)
            }
        } else {
            customSnackBarFail(
                requireContext(),
                binding.root,
                getString(R.string.user_detail_not_found)
            )
        }
    }

    override fun onClick(view: View?) {
        when (view) {
            binding.wearableContainer -> {
                findNavController().navigate(GetProfileFragmentDirections.getProfileToSyncHealthData())
            }
            binding.settingContainer -> {
                findNavController().navigate(
                    GetProfileFragmentDirections.getProfileToSettingFragment(
                        userType
                    )
                )
            }
            binding.headerView.imgEditProfile -> {
                findNavController().navigate(
                    GetProfileFragmentDirections.getProfileToEditProfile(
                        userModel
                    )
                )
            }
        }
    }

    override fun getToolbarImp(): IToolbar? {
        return ProfileToolbarImp(binding.headerView.toolBarContainer)
    }


}