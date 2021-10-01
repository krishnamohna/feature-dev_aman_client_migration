package com.cardio.doctor.ui.views.fragment.profile.profile

import android.app.Application
import android.net.Uri
import android.view.View
import android.widget.AdapterView
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.R
import com.cardio.doctor.data.local.UserManager
import com.cardio.doctor.domain.common.model.UserType
import com.cardio.doctor.domain.common.model.ValidationModel
import com.cardio.doctor.network.NetworkHelper
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.network.api.Constants
import com.cardio.doctor.ui.AppCardioPatient
import com.cardio.doctor.ui.common.base.viewmodel.BaseViewModel
import com.cardio.doctor.ui.common.utils.*
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
import com.google.firebase.firestore.DocumentSnapshot
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
@HiltViewModel
class UserProfileViewModel @Inject constructor(
    userManager: UserManager, private val repository: UserProfileRepository,
    application: Application, private val networkHelper: NetworkHelper,
) : BaseViewModel(userManager, application) {

    private val _userDetailDocument = SingleLiveEvent<Resource<DocumentSnapshot>>()
    val userDetailDocument: LiveData<Resource<DocumentSnapshot>> =
        _userDetailDocument

    private val _send_email_verification = SingleLiveEvent<Resource<String>>()
    val live_data_send_email_verification: LiveData<Resource<String>> =
        _send_email_verification

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
    var firebaseUri: Uri? = null

    val validationChannel = Channel<ValidationModel>(ENUM.INT_10)

    fun getUserDetail() {
        try {
            _userDetailDocument.postValue(Resource.loading(Constants.USER_DETAIL, null))
            viewModelScope.launch {
                var userDetail = repository.fetchUserDetail(_firebaseException)
                _userDetailDocument.postValue(Resource.success(Constants.USER_DETAIL, userDetail))
            }
        } catch (e: Exception) {
            _userDetailDocument.value =
                Resource.error(Constants.USER_DETAIL, 0, getExceptionMessage(e), null)
        }
    }

    fun isEmailVerified(): Boolean? {
        return repository.isEmailVerified()
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
                if (firebaseUri != null) {
                    _getUserProfilePic.value =
                        Resource.success(Constants.USER_PROFILE_PIC, firebaseUri)
                } else {
                    _userDetailDocument.value =
                        Resource.error(Constants.USER_PROFILE_PIC, 0, "", null)
                }
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
        firstName: String,
        lastName: String,
        email: String,
        countryCode: String,
        phoneNumber: String,
        dob: String,
        height: String,
        heartRate: String,
        selectedImageUri: Uri?,
        fileName: String?,
        userType: String?,
        isEmailEdited: (newEmail:String) -> Unit,
    ) {

        val context = getApplication<AppCardioPatient>()

        val isValidFirstName = when {
            firstName.isEmpty() -> {
                queueValidationRequest(
                    Status.ERROR,
                    context.getString(R.string.enter_first_name),
                    R.id.edtFirstName, R.id.tvFirstNameError
                )
                false
            }
            firstName.length < ENUM.INT_3 -> {
                queueValidationRequest(
                    Status.ERROR,
                    context.getString(R.string.enter_valid_first_name),
                    R.id.edtFirstName, R.id.tvFirstNameError
                )
                false
            }
            else -> {
                queueValidationRequest(
                    Status.SUCCESS, "",
                    R.id.edtFirstName, R.id.tvFirstNameError
                )
                true
            }
        }

        val isValidLastName = when {
            lastName.isNotEmpty() && lastName.length < ENUM.INT_3 -> {
                queueValidationRequest(
                    Status.ERROR,
                    context.getString(R.string.enter_valid_last_name),
                    R.id.edtLastName, R.id.tvLastName
                )
                false
            }
            else -> {
                queueValidationRequest(
                    Status.SUCCESS, "",
                    R.id.edtLastName, R.id.tvLastName
                )
                true
            }
        }

        val isValidEmail = when {
            email.isEmpty() -> {
                queueValidationRequest(
                    Status.ERROR,
                    context.getString(R.string.enter_email_address),
                    R.id.edtEmailId, R.id.tvEmailError
                )
                false
            }
            !isValidEmail(email) -> {
                queueValidationRequest(
                    Status.ERROR,
                    context.getString(R.string.err_valid_email),
                    R.id.edtEmailId, R.id.tvEmailError
                )
                false
            }
            else -> {
                queueValidationRequest(
                    Status.SUCCESS, "",
                    R.id.edtEmailId, R.id.tvEmailError
                )
                true
            }
        }

        //skip phone validation if user type is Google
        var isValidPhoneNumber = false
        if (userType != null || userType == UserType.GOOGLE.name) {
            isValidPhoneNumber = true
        } else {
            isValidPhoneNumber = when {
                phoneNumber.isEmpty() -> {
                    queueValidationRequest(
                        Status.ERROR,
                        context.getString(R.string.enter_valid_mobile),
                        R.id.edtPhoneNumber, R.id.tvPhoneNoError
                    )
                    false
                }
                !isValidMobileNumber(phoneNumber) -> {
                    queueValidationRequest(
                        Status.ERROR,
                        context.getString(R.string.err_valid_phone_number),
                        R.id.edtPhoneNumber, R.id.tvPhoneNoError
                    )
                    false
                }
                else -> {
                    queueValidationRequest(
                        Status.SUCCESS, "",
                        R.id.edtPhoneNumber, R.id.tvPhoneNoError
                    )
                    true
                }
            }
        }

        if (isValidFirstName && isValidLastName && isValidEmail && isValidPhoneNumber) {
            //check if email was edited
            if(repository.isEmailEdited(email)){
                //check if new email addresss already exist
                viewModelScope.launch {
                    var emailExist=repository.isEmailAlreadyExist(email,_firebaseException)
                    emailExist?.let {
                        if(emailExist){
                            queueValidationRequest(
                                Status.ERROR,
                                context.getString(R.string.err_email_exist),
                                R.id.edtEmailId, R.id.tvEmailError
                            )
                        }else {
                            isEmailEdited.invoke(email)
                        }
                    }
                }
            }else {
                updateUserDetailOnFireStore(
                    firstName,
                    lastName, email, countryCode, phoneNumber,
                    dob, height, heartRate, selectedImageUri, fileName, userType
                )
            }
        }
    }

     fun updateUserDetailOnFireStore(
        firstName: String,
        lastName: String,
        email: String,
        countryCode: String,
        phoneNumber: String,
        dob: String,
        height: String,
        heartRate: String,
        selectedImageUri: Uri?,
        fileName: String?,
        userType: String?,
    ) {
        try {
            val context = getApplication<AppCardioPatient>()
            if (!networkHelper.isNetworkConnected())
                _editProfileResponse.value = Resource.error(
                    Constants.EDIT_PROFILE, 0,
                    context.getString(R.string.err_no_network_available), null
                )
            else {
                _editProfileResponse.value = Resource.loading(Constants.EDIT_PROFILE, null)
                viewModelScope.launch {
                    //lets upload image first if user has selected new image
                    selectedImageUri?.let {
                        firebaseUri = withContext(Dispatchers.IO) {
                            repository.uploadImageOnFirebaseStorage(
                                selectedImageUri, fileName!!, _firebaseException
                            )
                        }
                    }
                    //get public path from uri
                    var imagePath = ""
                    firebaseUri?.let { imagePath = it.toString() }
                    // save all user info now
                    repository.firebaseAuth.currentUser?.let {
                        val user: HashMap<String, Any?> =
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
                                FireStoreDocKey.WEIGHT to heartRate,
                                FireStoreDocKey.IMAGE_URL to imagePath,
                                FireStoreDocKey.SIGN_UP_TYPE to userType
                            )

                        if (auth.currentUser == null) {
                            _editProfileResponse.value = Resource.error(
                                Constants.EDIT_PROFILE,
                                0, context.getString(R.string.getting_some_error), null
                            )
                        } else {
                            val currentUser = auth.currentUser
                            val isUpdated =
                                repository.storeUserDataInFireStore(
                                    currentUser!!,
                                    user, _firebaseException
                                )
                            if (isUpdated == true) {
                                _editProfileResponse.value =
                                    Resource.success(
                                        Constants.EDIT_PROFILE,
                                        context.getString(R.string.profile_updated_successfully)
                                    )
                            } else {
                                _editProfileResponse.value = Resource.error(
                                    Constants.EDIT_PROFILE, 0,
                                    context.getString(R.string.getting_some_error), null
                                )
                            }
                        }
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
        validationChannel.send(
            ValidationModel(
                edtResource, tvResourceId, status, message
            )
        )
    }

    private fun showFailureException(exception: Exception) {
        _editProfileResponse.value = Resource.error(
            Constants.VALIDATION,
            0, getExceptionMessage(exception),
            null
        )
    }

    fun getSelectedHealthKit(): String {
        var selectedTab = userManager.getString(Preference.SYNC_HEALTH)
        if (selectedTab.isEmpty())
            selectedTab = getApplication<AppCardioPatient>().getString(R.string.fitbit)
        return selectedTab
    }

    fun sendEmailVerificationLink() {
        viewModelScope.launch {
            _send_email_verification.postValue(Resource.loading(Constants.EMAIL_SEND_VERIFICATION, null))
            repository.sendVerificationEmail(_firebaseException)
            _send_email_verification.postValue(Resource.success(Constants.EMAIL_SEND_VERIFICATION, applicationContext.getString(R.string.message_verification_email_sent)))
        }
    }

}
