package com.cardio.physician.data.remote.signup

import androidx.lifecycle.MutableLiveData
import com.cardio.physician.domain.common.repository.BaseRepository
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.firebaseQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import java.util.*
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(firebaseAuth, fireStore, storageReference, apiService) {

    suspend fun isEmailAlreadyExist(
        email: String,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<SignInMethodQueryResult, Boolean>(
        operation = {
            firebaseAuth.fetchSignInMethodsForEmail(email)
                    },
        parse = { result ->
            return@firebaseQuery result.signInMethods?.size ?: 0 > 0
        }, errorLiveData
    )

    suspend fun isPhoneNumberExist(
        phoneNumber: String,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<QuerySnapshot, Boolean>(
        operation = { fireStore.collection(FireStoreCollection.USERS).get() },
        parse = { querySnapshot ->
            for (document in querySnapshot) {
                var phoneNo = document.data[FireStoreDocKey.PHONE_NUMBER] as? String?
                phoneNo?.let {
                    if(it.isNotBlank() && phoneNumber.contains(it)) {
                        return@firebaseQuery true
                    }
                }
            }
            return@firebaseQuery false
        },
        errorLiveData = errorLiveData
    )

}

