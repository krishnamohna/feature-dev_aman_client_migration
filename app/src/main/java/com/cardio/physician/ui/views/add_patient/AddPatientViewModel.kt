package com.cardio.physician.ui.views.add_patient

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.viewModelScope
import com.cardio.physician.data.local.UserManager
import com.cardio.physician.data.remote.addpatient.AddPatientRepositoryImp
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.Constants
import com.cardio.physician.ui.common.base.viewmodel.BaseAuthViewModel
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.UserType
import com.cardio.physician.ui.common.utils.getCurrentDate
import com.cardio.physician.ui.common.utils.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.HashMap
import javax.inject.Inject

@Suppress("UNUSED_PARAMETER")
@HiltViewModel
class AddPatientViewModel @Inject constructor(
    userManager: UserManager, private val addPatientRepositoryImp: AddPatientRepositoryImp,
    application: Application
) : BaseAuthViewModel(userManager, application) {

    private val userSearchSingleLiveData = MediatorLiveData<Resource<List<PatientModel>>>()
    val liveUserData: LiveData<Resource<List<PatientModel>>> =
        userSearchSingleLiveData

    fun searchUserInFirestore(searchString: String) {
            try {
                viewModelScope.launch {
                    val data = addPatientRepositoryImp.getPatientList(searchString)
                    if(data.isEmpty()){
                        searchUserInFirestoreWithEmail(searchString)
                    }else{
                        userSearchSingleLiveData.postValue(
                            Resource.success(
                                Constants.ADD_PATIENT,
                                data
                            )
                        )
                    }
                }
            }catch (e: Exception){
                userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 202, "202", null)
                checkValidEmailAddress(searchString)
            }
    }

    private fun searchUserInFirestoreWithEmail(searchString: String){
        try {
            viewModelScope.launch {
                val data = addPatientRepositoryImp.getPatientListWithEmail(searchString)
                if(data.isEmpty()){
                    userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 202, "202", null)
                    checkValidEmailAddress(searchString)
                }else{
                    userSearchSingleLiveData.postValue(
                        Resource.success(
                            Constants.ADD_PATIENT,
                            data
                        )
                    )
                }
            }
        }catch (e: Exception){
            userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 202, "202", null)
            checkValidEmailAddress(searchString)
        }
    }

    private fun checkValidEmailAddress(searchString: String) {
        if(!isValidEmail(searchString)){
            userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 203, "203", null)
        }else{
            userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 201, "201", null)
        }
    }

    fun getAllPatientList(){
        try {
            viewModelScope.launch {
                val data = addPatientRepositoryImp.getPatientListByDate(getCurrentDate())
                if(data.isEmpty()){
                    userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 202, "202", null)
                }else{
                    userSearchSingleLiveData.postValue(
                        Resource.success(
                            Constants.ADD_PATIENT,
                            data
                        )
                    )
                }
            }
        }catch (e: Exception){
            userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 202, "202", null)
        }
    }

    fun updateDataToFirestore(patientModel: PatientModel) {
        val connectionData: HashMap<String, Any> =
            hashMapOf(
                FireStoreDocKey.TIMESTAMP to System.currentTimeMillis(),
                FireStoreDocKey.REQUEST_STATUS to false,
                FireStoreDocKey.FIRST_NAME to (patientModel.firstName?:""),
                FireStoreDocKey.LAST_NAME to (patientModel.lastName?:""),
                FireStoreDocKey.SEARCH_NAME to (patientModel.firstName?.lowercase() +" "+ patientModel.lastName?.lowercase()),
                FireStoreDocKey.IMAGE_URL to (patientModel.imageUrl?:""),
                FireStoreDocKey.USER_TYPE to UserType.USER_TYPE_PHYSICIAN,
            )
        addPatientRepositoryImp.addDataToFirestore(patientModel.userId, connectionData)
    }
}