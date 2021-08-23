package com.cardio.doctor.ui.fragment.signup

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.Constants
import com.cardio.doctor.base.viewmodel.BaseViewModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.utils.*
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.android.gms.tasks.Tasks
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SignUpViewModel @Inject constructor(
    userManager: UserManager, signUpRepository: SignUpRepository,
    application: Application
) : BaseViewModel(userManager, signUpRepository, application) {

    private val _signUpApiResponse = SingleLiveEvent<Resource<String>>()
    val signUpApiResponse: LiveData<Resource<String>> = _signUpApiResponse

    fun validateFields(fullName: String, phoneNumber: String, countryCode : String ,email: String,
                       password: String) {
        val context = getApplication<AppCardioPatient>()
        when {
            fullName.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_valid_full_name))
                return
            }

            phoneNumber.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_valid_mobile))
                return
            }

            !isValidMobileNumber(phoneNumber) -> {
                showValidationMessage(context.getString(R.string.err_valid_mobile_number))
                return
            }

            isPhoneNumberExist(countryCode.plus(phoneNumber)) ->{
                return
            }

            email.isEmpty() -> {
                showValidationMessage(context.getString(R.string.enter_email_address))
                return
            }

            !isValidEmail(email) -> {
                showValidationMessage(context.getString(R.string.err_valid_email))
                return
            }
            /*   isEmailAlreadyExist(email) -> {
                   return
               }*/

            password.isEmpty() -> {
                showValidationMessage(context.getString(R.string.err_empty_password))
                return
            }

            !isValidPassword(password) -> {
                showValidationMessage(context.getString(R.string.err_valid_password))
                return
            }

            else ->  {
                storePhoneNumberInFirestore(countryCode.plus(phoneNumber))
                /*viewModelScope.launch {
                    val emailDeferred = async { isEmailAlreadyExist(email) }
                    val phoneNumberDeferred = async { isPhoneNumberExist(email) }
                    val isEmailExist= emailDeferred.await()
                    val isPhoneNumberExist = phoneNumberDeferred.await()

                    if(!isEmailExist && !isPhoneNumberExist){
                        storePhoneNumberInFirestore(countryCode.plus(phoneNumber))
                    }
                }*/
            }
        }
    }

    private fun showValidationMessage(message: String) {
        _signUpApiResponse.value = Resource.error(Constants.SIGNUP, 0, message, null)
    }

    fun isEmailAlreadyExist(email: String): Boolean {
        var emailAlreadyExist = false
        auth.fetchSignInMethodsForEmail(email).addOnCompleteListener { task ->
            task.result?.let {
                if (task.result?.signInMethods?.size!! > 0) {
                    emailAlreadyExist = true
                    showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_email_exist))
                } //else emailAlreadyExist = false
            }
        }.addOnFailureListener {
                e -> e.printStackTrace()
            emailAlreadyExist = true
        }
        return emailAlreadyExist
    }

    private fun storePhoneNumberInFirestore(phoneNumber: String) {
        val user: HashMap<String, Any> = hashMapOf(FireStoreDocKey.PHONE_NUMBER to phoneNumber)
        db.collection(FireStoreCollection.USERS).document().set(user)
            .addOnCompleteListener {
                if(it.isSuccessful){
                    _signUpApiResponse.value = Resource.success(Constants.SIGNUP, null)
                }
            }
            .addOnFailureListener {
                showValidationMessage(
                    it.localizedMessage
                        ?: getApplication<AppCardioPatient>().getString(R.string.getting_some_error)
                )
            }
    }

    fun isPhoneNumberExist(phoneNumber: String) : Boolean{
        var isPhoneNumberExist = false
        db.collection(FireStoreCollection.USERS).get().addOnSuccessListener { result ->
            for (document in result) {
                if (phoneNumber.contains(
                        document.data[FireStoreDocKey.PHONE_NUMBER] as String,
                        true
                    )
                ) {
                    showValidationMessage(getApplication<AppCardioPatient>().getString(R.string.err_phonenumber_already_exist))
                    isPhoneNumberExist = true
                }
                if(!isPhoneNumberExist){
                    storePhoneNumberInFirestore(phoneNumber)
                }
            }
        }.addOnFailureListener { exception ->
            isPhoneNumberExist = true
            showValidationMessage(
                exception.localizedMessage
                    ?: getApplication<AppCardioPatient>().getString(R.string.getting_some_error)
            )
        }
        return isPhoneNumberExist
    }
}
