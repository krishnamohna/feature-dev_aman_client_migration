package com.cardio.doctor.ui.fragment.profile.profile

import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.fragment.AppBaseFragment
import com.cardio.doctor.databinding.FragmentGetProfileBinding
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.utils.FireStoreDocKey
import com.cardio.doctor.utils.customSnackBarFail
import com.cardio.doctor.utils.viewbinding.viewBinding
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GetProfileFragment :  AppBaseFragment(R.layout.fragment_get_profile), View.OnClickListener{
    private val binding by viewBinding(FragmentGetProfileBinding::bind)
    private val viewModel: UserProfileViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpToolbar(binding.root, getString(R.string.profile), backBtnVisibility = true,
            editProfile = true)
        setListener()
        setObservers()
        viewModel.getUserDetail()
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
        viewModel.uploadUserProfilePic.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })
        viewModel.getUserProfilePic.observe(viewLifecycleOwner, {
            handleApiCallback(it)
        })

    }

    private fun handleApiCallback(apiResponse: Resource<Any>) {
        when (apiResponse.status) {
            Status.SUCCESS -> {
                hideProgress()
                when (apiResponse.apiConstant) {
                    Constants.USER_DETAIL -> showUserDetailOnUI(apiResponse.data as DocumentSnapshot)

                    Constants.USER_PROFILE_PIC ->{
                        val storageReference = apiResponse.data as Uri
                        Glide.with(requireContext())
                            .load(storageReference)
                            .apply(RequestOptions().circleCrop())
                            .placeholder(R.drawable.ic_profile_placeholder)
                            .into(binding.imgProfilePic)
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

            Status.ALPHA -> {}
        }
    }

    private fun showUserDetailOnUI(documentReference: DocumentSnapshot?) {
        Log.d("TAG", "showUserDetailOnUI() called ${documentReference.toString()}")
        if(documentReference !=null && documentReference.data !=null){
            val firstName = documentReference.data?.get(FireStoreDocKey.FIRST_NAME) as String?
            val lastName = documentReference.data?.get(FireStoreDocKey.LAST_NAME) as String?
            val email = documentReference.data?.get(FireStoreDocKey.EMAIL) as String?
            val countryCode = documentReference.data?.get(FireStoreDocKey.COUNTRY_CODE) as String?
            val phoneNumber = documentReference.data?.get(FireStoreDocKey.PHONE_NUMBER) as String?
            val imageUrl = documentReference.data?.get(FireStoreDocKey.IMAGE_URL) as String?
            var gender = documentReference.data?.get(FireStoreDocKey.GENDER) as String?
            var weight = documentReference.data?.get(FireStoreDocKey.WEIGHT) as String?
            var height = documentReference.data?.get(FireStoreDocKey.HEIGHT) as String?

            val userName = firstName?.plus(" ").plus(lastName ?: "")
            binding.txtUserName.text = userName
            binding.txtEmailAddress.text = email ?: ""
            binding.txtPhoneNumber.text = countryCode?.plus(" ").plus(phoneNumber)

            if(gender.isNullOrEmpty()){
                gender = "NA"
            }else if(gender.equals(getString(R.string.select_gender),true)){
                gender = "NA"
            }
            height = if(!height.isNullOrEmpty()) height else "NA"
            weight = if(!weight.isNullOrEmpty()) weight else "NA"

            binding.txtGender.text = gender
            binding.txtHeight.text = height
            binding.txtWeight.text = weight

            binding.txtSelectedWearable.text = viewModel.getSelectedHealthKit()
            if(!imageUrl.isNullOrEmpty()) {
                viewModel.getImageDownloadUrl(imageUrl)
            }
        }else {
            customSnackBarFail(requireContext(),binding.root,getString(R.string.user_detail_not_found))
        }
    }

    override fun onClick(view: View?) {
        when(view){
            binding.wearableContainer ->{
                baseViewModel.setDirection(GetProfileFragmentDirections.getProfileToSyncHealthData())
            }
            binding.settingContainer ->{
                baseViewModel.setDirection(GetProfileFragmentDirections.getProfileToSettingFragment())
            }
            binding.headerView.imgEditProfile ->{
                baseViewModel.setDirection(GetProfileFragmentDirections.getProfileToEditProfile())
            }
        }
    }

}