package com.cardio.doctor.ui.fragment.profile.profile

import android.app.Application
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.model.ValidationModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.FireStoreDocKey
import com.cardio.doctor.utils.isValidEmail
import com.cardio.doctor.utils.isValidMobileNumber
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
@HiltViewModel
class UserProfileViewModel @Inject constructor(
    userManager: UserManager, private val repository: UserProfileRepository,
    application: Application,
) : BaseViewModel(userManager, repository, application) {

    private val _userDetailDocument = SingleLiveEvent<Resource<DocumentSnapshot>>()
    val userDetailDocument: LiveData<Resource<DocumentSnapshot>> =
        _userDetailDocument

    private val _uploadUserProfilePic = SingleLiveEvent<Resource<Uri>>()
    val uploadUserProfilePic: LiveData<Resource<Uri>> =
        _uploadUserProfilePic

    private val _getUserProfilePic = SingleLiveEvent<Resource<Uri>>()
    val getUserProfilePic: LiveData<Resource<Uri>> =
        _getUserProfilePic

    private val _userGender = SingleLiveEvent<Resource<String>>()
    val userGender: LiveData<Resource<String>> = _userGender

    private val _editProfileResponse = SingleLiveEvent<Resource<String>>()
    val editProfileResponse: LiveData<Resource<String>> =
        _editProfileResponse

    private var gender: String = ""
    var firebaseUri: Uri? =null

    val validationChannel = Channel<ValidationModel>(ENUM.INT_10)

    fun getUserDetail() {
        try {
            _userDetailDocument.value = Resource.loading(Constants.USER_DETAIL, null)
            viewModelScope.launch {
                val userDeferred = async { repository.fetchUserDetail(_firebaseException)}
                val userDetail = userDeferred.await()
                _userDetailDocument.value = Resource.success(Constants.USER_DETAIL, userDetail)
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_DETAIL, 0, getExceptionMessage(e), null)
        }
    }

    fun uploadProfileImage(deviceUri: Uri?, fileName: String) {
        try {
            _uploadUserProfilePic.value = Resource.loading(Constants.UPLOAD_PROFILE_PIC, null)
            viewModelScope.launch {
                val firebaseUriDeferred = async {
                    repository.uploadImageOnFirebaseStorage(
                        deviceUri, fileName, _firebaseException
                    )
                }
                val firebaseUri = firebaseUriDeferred.await()
                _uploadUserProfilePic.value =
                    Resource.success(Constants.UPLOAD_PROFILE_PIC, firebaseUri)
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_PROFILE_PIC, 0, getExceptionMessage(e), null)
        }
    }

    fun getImageDownloadUrl(url: String) {
        try {
            //_getUserProfilePic.value = Resource.loading(Constants.USER_PROFILE_PIC, null)
            viewModelScope.launch {
                val firebaseUriDeferred = async { repository.getImageUrl(url, _firebaseException) }
                val firebaseUri = firebaseUriDeferred.await()
                _getUserProfilePic.value =
                    Resource.success(Constants.USER_PROFILE_PIC, firebaseUri)
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_PROFILE_PIC, 0, getExceptionMessage(e), null)
        }
    }

    fun selectGender(parent: AdapterView<*>?, view: View?, pos: Int, id: Long) {
        gender = parent?.selectedItem.toString()
        _userGender.value = Resource.success(Constants.USER_GENDER, gender)
    }

    suspend fun validateFields(
        firstName: String, lastName: String, email: String, countryCode: String,
        phoneNumber: String, dob: String, height: String, heartRate: String,
    ) {

        val context = getApplication<AppCardioPatient>()

        val isValidFirstName = when {
            firstName.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_first_name),
                    R.id.edtFirstName, R.id.tvFirstNameError)
                false
            }
            firstName.length < ENUM.INT_3 -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_first_name),
                    R.id.edtFirstName, R.id.tvFirstNameError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtFirstName, R.id.tvFirstNameError)
                true
            }
        }

        val isValidLastName = when {
            lastName.isNotEmpty() && lastName.length < ENUM.INT_3 -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_last_name),
                    R.id.edtLastName, R.id.tvLastName)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtLastName, R.id.tvLastName)
                true
            }
        }

        val isValidEmail = when {
            email.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_email_address),
                    R.id.edtEmailId, R.id.tvEmailError)
                false
            }
            !isValidEmail(email) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_email),
                    R.id.edtEmailId, R.id.tvEmailError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtEmailId, R.id.tvEmailError)
                true
            }
        }

        val isValidPhoneNumber = when {
            phoneNumber.isEmpty() -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.enter_valid_mobile),
                    R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                false
            }
            !isValidMobileNumber(phoneNumber) -> {
                queueValidationRequest(Status.ERROR,
                    context.getString(R.string.err_valid_phone_number),
                    R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                false
            }
            else -> {
                queueValidationRequest(Status.SUCCESS, "",
                    R.id.edtPhoneNumber, R.id.tvPhoneNoError)
                true
            }
        }

        if (isValidFirstName && isValidLastName && isValidEmail && isValidPhoneNumber) {
            updateUserDetailOnFireStore(firstName,
                lastName, email, countryCode, phoneNumber,
                dob, height, heartRate)
        }
    }

    private fun updateUserDetailOnFireStore(
        firstName: String, lastName: String, email: String,
        countryCode: String, phoneNumber: String, dob: String,
        height: String, heartRate: String) {
        try {
            viewModelScope.launch {
                _editProfileResponse.value = Resource.loading(Constants.EDIT_PROFILE, null)
                val context = getApplication<AppCardioPatient>()
                var imagePath = ""
                firebaseUri?.let { imagePath = it.toString() }

                repository.firebaseAuth.currentUser?.let {
                    val user: HashMap<String, Any> =
                        hashMapOf(
                            FireStoreDocKey.USER_ID to repository.firebaseAuth.currentUser?.uid!!,
                            FireStoreDocKey.FIRST_NAME to firstName,
                            FireStoreDocKey.LAST_NAME to lastName,
                            FireStoreDocKey.EMAIL to email,
                            FireStoreDocKey.COUNTRY_CODE to countryCode,
                            FireStoreDocKey.PHONE_NUMBER to phoneNumber,
                            FireStoreDocKey.GENDER to gender,
                            FireStoreDocKey.DOB to dob,
                            FireStoreDocKey.HEIGHT to height,
                            FireStoreDocKey.HEART_RATE to heartRate,
                            FireStoreDocKey.IMAGE_URL to imagePath
                        )
                    val deferredUpdatedDetail = async { repository.storeUserDataInFireStore(user) }
                    val isUpdated = deferredUpdatedDetail.await()
                    if (isUpdated) {
                        _editProfileResponse.value = Resource.success(Constants.EDIT_PROFILE,
                            context.getString(R.string.profile_updated_successfully))
                    } else {
                        _editProfileResponse.value = Resource.error(
                            Constants.EDIT_PROFILE, 0, context.getString(R.string.getting_some_error),
                            null)
                    }
                }

            }
        } catch (e: Exception) {
            showFailureException(e)
        }
    }

    private suspend fun queueValidationRequest(
        status: Status, message: String,
        edtResource: Int, tvResourceId: Int,
    ) {
        validationChannel.send(ValidationModel(
            edtResource, tvResourceId, status, message
        ))
    }

    private fun showFailureException(exception: Exception) {
        _editProfileResponse.value = Resource.error(
            Constants.VALIDATION,
            0, getExceptionMessage(exception),
            null
        )
    }


}
