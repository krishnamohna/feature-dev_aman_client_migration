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
import com.cardio.physician.ui.common.utils.*
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
                    val connectedPatientList = addPatientRepositoryImp.getConnectedPatientList()
                    val data = addPatientRepositoryImp.getPatientList(searchString)
                    if(data.isEmpty()){
                        searchUserInFirestoreWithEmail(searchString)
                    }else{
                        val map = LinkedHashMap<String?, PatientModel>()
                        for (patient in data){
                            map.put(patient.userId, patient)
                        }
                        for (connected in connectedPatientList){
                            if(map.containsKey(connected.userId)) map.put(connected.userId, connected)
                        }
                        val outputString = ArrayList<PatientModel>()
                        outputString.addAll(map.values)
                        userSearchSingleLiveData.postValue(
                            Resource.success(
                                Constants.ADD_PATIENT,
                                outputString
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
                val connectedPatientList = addPatientRepositoryImp.getConnectedPatientList()
                val data = addPatientRepositoryImp.getPatientListWithEmail(searchString)
                if(data.isEmpty()){
                    userSearchSingleLiveData.value = Resource.error(Constants.ADD_PATIENT, 202, "202", null)
                    checkValidEmailAddress(searchString)
                }else{
                    val map = LinkedHashMap<String?, PatientModel>()
                    for (patient in data){
                        patient.firstName = patient.email
                        patient.lastName = ""
                        map.put(patient.userId, patient)
                    }
                    for (connected in connectedPatientList){
                        if(map.containsKey(connected.userId)) {
                            connected.firstName = connected.email
                            connected.lastName = ""
                            map.put(connected.userId, connected)
                        }
                    }
                    val outputString = ArrayList<PatientModel>()
                    outputString.addAll(map.values)
                    userSearchSingleLiveData.postValue(
                        Resource.success(
                            Constants.ADD_PATIENT,
                            outputString
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
                    val map = LinkedHashMap<String?, PatientModel>()
                    for (patient in data){
                        if(patient.isAdded == 1) map.put(patient.userId, patient)
                    }
                    val outputString = ArrayList<PatientModel>()
                    outputString.addAll(map.values)
                    userSearchSingleLiveData.postValue(
                        Resource.success(
                            Constants.ADD_PATIENT,
                            outputString
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
                FireStoreDocKey.TIMESTAMP to System.currentTimeMillis().toDouble(),
                FireStoreDocKey.REQUEST_STATUS to false,
                FireStoreDocKey.FIRST_NAME to (patientModel.firstName?:""),
                FireStoreDocKey.LAST_NAME to (patientModel.lastName?:""),
                FireStoreDocKey.SEARCH_NAME to (patientModel.firstName?.lowercase() +" "+ patientModel.lastName?.lowercase()),
                FireStoreDocKey.IMAGE_URL to (patientModel.imageUrl?:""),
                FireStoreDocKey.USER_ID to (patientModel.userId?:""),
                FireStoreDocKey.EMAIL to (patientModel.email?:"")
            )
        val connection1Data: HashMap<String, Any> =
            hashMapOf(
                FireStoreDocKey.TIMESTAMP to System.currentTimeMillis().toDouble(),
                FireStoreDocKey.REQUEST_STATUS to false,
                FireStoreDocKey.FIRST_NAME to userManager.getString(Preference.PREF_FIRST_NAME),
                FireStoreDocKey.LAST_NAME to userManager.getString(Preference.PREF_LAST_NAME),
                FireStoreDocKey.SEARCH_NAME to (userManager.getString(Preference.PREF_FIRST_NAME).lowercase() +" "+ userManager.getString(Preference.PREF_LAST_NAME).lowercase()),
                FireStoreDocKey.IMAGE_URL to userManager.getString(Preference.PREF_PROFILE_IMAGE),
                FireStoreDocKey.USER_ID to getPatientUid(),
                FireStoreDocKey.EMAIL to userManager.getString(Preference.PREF_EMAIL)
            )
        viewModelScope.launch {
            addPatientRepositoryImp.addDataToFirestore(patientModel, connectionData, connection1Data)
        }
    }
}