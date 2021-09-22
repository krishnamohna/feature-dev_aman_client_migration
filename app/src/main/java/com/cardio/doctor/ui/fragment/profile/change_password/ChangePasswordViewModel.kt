package com.cardio.doctor.ui.fragment.profile.change_password

import android.app.Application
import android.text.TextUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.api.Constants.Companion.CHANGE_PASSWORD
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.model.ValidationModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.ENUM
import com.cardio.doctor.utils.isMatched
import com.cardio.doctor.utils.isValidPassword
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.cardio.doctor.utils.validUserNameLength
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChangePasswordViewModel @Inject constructor(
    userManager: UserManager, private val repository: ChangePasswordRepository,
    application: Application,
) : BaseViewModel(userManager, repository, application) {

    private val _changePasswordResponse = SingleLiveEvent<Resource<Boolean>>()
    val changePasswordResponse: LiveData<Resource<Boolean>> = _changePasswordResponse

    val validationChannel = Channel<ValidationModel>(ENUM.INT_10)

    fun validateFieldsToSetAlpha(
        oldPassword: String,
        newPassword: String,
        confirmPassword: String,
    ) {
        when {
            TextUtils.isEmpty(oldPassword) ||
                    !newPassword.validUserNameLength() -> {
                setObserverForAlpha(R.string.alpha_false)
                return
            }
            TextUtils.isEmpty(newPassword) ||
                    !newPassword.validUserNameLength() -> {
                setObserverForAlpha(R.string.alpha_false)
                return
            }
            TextUtils.isEmpty(confirmPassword) ||
                    !confirmPassword.validUserNameLength() -> {
                setObserverForAlpha(R.string.alpha_false)
                return
            }
            else -> setObserverForAlpha(R.string.alpha_true)
        }
    }

    private fun setObserverForAlpha(resourceId: Int) {
        _changePasswordResponse.value = Resource.setAlpha(CHANGE_PASSWORD, resourceId)
    }

    fun validatePassword(oldPassword: String, newPassword: String, confirmPassword: String) {
        val context = getApplication<AppCardioPatient>()
        viewModelScope.launch {

            val isValidOldPassword = when {
                oldPassword.isEmpty() -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.please_enter_your_old_password),
                        R.id.edtOldPassword, R.id.tvOldPasswordError)
                    false
                }
                !isValidPassword(oldPassword) -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.err_valid_password),
                        R.id.edtOldPassword, R.id.tvOldPasswordError)
                    false
                }
                else -> {
                    queueValidationRequest(Status.SUCCESS, "",
                        R.id.edtOldPassword, R.id.tvOldPasswordError)
                    true
                }
            }

            val isValidNewPassword = when {
                newPassword.isEmpty() -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.err_empty_password),
                        R.id.edtNewPassword, R.id.tvNewPasswordError)
                    false
                }
                !isValidPassword(newPassword) -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.err_valid_password),
                        R.id.edtNewPassword, R.id.tvNewPasswordError)
                    false
                }
                else -> {
                    queueValidationRequest(Status.SUCCESS, "",
                        R.id.edtNewPassword, R.id.tvNewPasswordError)
                    true
                }
            }

            val isValidConfirmPassword = when {
                confirmPassword.isEmpty() -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.err_confirm_password),
                        R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                    false
                }
                !isValidPassword(confirmPassword) -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.err_valid_password),
                        R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                    false
                }
                !newPassword.equals(confirmPassword, true) -> {
                    queueValidationRequest(Status.ERROR,
                        context.getString(R.string.password_does_not_match),
                        R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                    false
                }
                else -> {
                    queueValidationRequest(Status.SUCCESS, "",
                        R.id.edtConfirmPassword, R.id.tvConfirmPasswordError)
                    true
                }
            }
            if (isValidOldPassword && isValidNewPassword && isValidConfirmPassword) {
                if (isMatched(oldPassword, confirmPassword)) {
                    _changePasswordResponse.value = Resource.requiredResource(CHANGE_PASSWORD,
                        R.string.old_and_new_password_should_not_same)
                } else
                    changePassword(oldPassword, newPassword)
            }
        }
    }


    private fun changePassword(oldPass: String, newPass: String) {
        viewModelScope.launch {
            _changePasswordResponse.value = Resource.loading(CHANGE_PASSWORD, null)
            val currentUser = auth.currentUser
            if (currentUser == null) {
                // _changePasswordResponse.value = Resource.error(CHANGE_PASSWORD, true)

            } else {
                val isUserAuthenticated =
                    repository.reAuthenticateUser(currentUser, oldPass, _firebaseException)
                if(isUserAuthenticated == true){
                    val isPasswordUpdated =
                        repository.updatePassword(currentUser, newPass, _firebaseException)
                    if(isPasswordUpdated == true){
                        _changePasswordResponse.value = Resource.success(CHANGE_PASSWORD, true)
                        return@launch
                    }
                }
            }
            /*if(isPasswordChanged !=null && isPasswordChanged){
                _changePasswordResponse.value = Resource.success(CHANGE_PASSWORD, true)
            }*/
            /*  val credential = EmailAuthProvider.getCredential(auth.currentUser?.email ?: "", oldPass)
              auth.currentUser?.reauthenticate(credential)?.addOnCompleteListener {
                  if (it.isSuccessful) {
                      auth.currentUser?.updatePassword(newPass)?.addOnCompleteListener { task ->
                          if (task.isSuccessful) {
                              _changePasswordResponse.value = Resource.success(CHANGE_PASSWORD, true)
                          } else {
                              Log.d("TAG", "Error password not updated")
                          }
                      }
                  } else {
                      Log.d("TAG", "Error auth failed")
                  }
              }*/
        }
    }

    private fun showValidationMessage(message: String) {
        _changePasswordResponse.value = Resource.error(Constants.VALIDATION, 0, message, null)
    }

    private suspend fun queueValidationRequest(
        status: Status, message: String,
        edtResource: Int, tvResourceId: Int,
    ) {
        validationChannel.send(ValidationModel(
            edtResource, tvResourceId, status, message
        ))
    }

}
