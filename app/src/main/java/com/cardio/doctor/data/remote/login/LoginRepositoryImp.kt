package com.cardio.doctor.data.remote.login

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.domain.login.LoginRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.api.ApiService
import com.cardio.doctor.ui.common.utils.FireStoreCollection
import com.cardio.doctor.ui.common.utils.FireStoreDocKey
import com.cardio.doctor.ui.common.utils.firebaseQuery
import com.google.firebase.auth.*
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject

class LoginRepositoryImp @Inject constructor(
        val firebaseAuth: FirebaseAuth,
        private val fireStore: FirebaseFirestore,
        private val storageReference: StorageReference,
        apiService: ApiService,
) : LoginRepositary {


    override suspend fun googleSignInWithCredential(
            authCredential: AuthCredential,
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<AuthResult, String?>(
            operation = {
                firebaseAuth.signInWithCredential(authCredential)
            }, parse = {
        return@firebaseQuery firebaseAuth?.currentUser?.uid
    }, errorLiveData

    )


    override suspend fun linkGoogleCredentialWithExistingAcc(
            @NonNull credential: AuthCredential, errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<AuthResult?, Boolean>(
            operation = {
                firebaseAuth.currentUser?.linkWithCredential(credential)!!
            }, parse = {
        Log.d("TAG", "linkGoogleCredentialWithExistingAcc() called ${it.toString()}")
        return@firebaseQuery it != null
    }, errorLiveData
    )

    override suspend fun isCollectionExist(uuid: String, errorLiveData: MutableLiveData<Resource<Exception>>) =
            firebaseQuery<DocumentSnapshot, Boolean?>(
                    operation = {
                        fireStore.collection(FireStoreCollection.USERS).document(uuid ?: "").get()
                    },
                    parse = { documentSnapShot ->
                        return@firebaseQuery documentSnapShot.exists()
                    }, errorLiveData
            )

    override suspend fun isEmailExistForNormalSignUp(
            email: String,
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<SignInMethodQueryResult, Boolean>(
            operation = { firebaseAuth.fetchSignInMethodsForEmail(email) },
            parse = { result ->
                var isExist = false
                result.signInMethods?.forEach {
                    if (it == EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD) {
                        return@firebaseQuery true
                    }
                }
                return@firebaseQuery false
            }, errorLiveData
    )


    override suspend fun isPhoneNumberExist(
            phoneNumber: String,
            errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<QuerySnapshot, Boolean>(
            operation = { fireStore.collection(FireStoreCollection.USERS).get() },
            parse = { querySnapshot ->
                for (document in querySnapshot) {
                    if (phoneNumber.contains(document.data[FireStoreDocKey.PHONE_NUMBER] as String)) {
                        return@firebaseQuery true
                    }
                }
                return@firebaseQuery false
            },
            errorLiveData = errorLiveData
    )


    override suspend fun storeUserDataInFireStore(
            childName: String,
            hashMap: HashMap<String, Any?>,
    ): Boolean {
        return try {
            val userId = firebaseAuth.currentUser?.uid
            fireStore.collection(FireStoreCollection.USERS)
                    .document(userId ?: "")
                    .set(hashMap)
                    .await()
            true
        } catch (e: Exception) {
            false
        }
    }

}

