package com.cardio.physician.data.remote.profile

import androidx.lifecycle.MutableLiveData
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.common.repository.BaseRepository
import com.cardio.physician.network.Resource
import com.cardio.physician.network.api.ApiService
import com.cardio.physician.ui.common.utils.FireStoreCollection
import com.cardio.physician.ui.common.utils.extentions.toUserModel
import com.cardio.physician.ui.common.utils.firebaseDocumentQuery
import com.cardio.physician.ui.common.utils.firebaseQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserProfileRepository @Inject constructor(
    override val firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(
    firebaseAuth, fireStore, storageReference, apiService
) {

    suspend fun fetchUserDetailByModel(userId:String?): UserModel {
        val patientId = userId?: firebaseAuth.currentUser?.uid
        return fireStore.collection(FireStoreCollection.USERS).document(patientId ?: "")
            .get().await().toUserModel()
    }


    suspend fun storeUserDataInFireStore(
        firebaseUser: FirebaseUser,
        hashMap: HashMap<String, Any?>,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseDocumentQuery(
        operation = {
            val userId = firebaseUser.uid
            fireStore.collection(FireStoreCollection.USERS).document(userId ?: "")
                .set(hashMap)
                .await()
        }, parse = {
            return@firebaseDocumentQuery true
        }, errorLiveData
    )

    fun isEmailVerified(): Boolean? {
        return firebaseAuth.currentUser?.isEmailVerified
    }

    suspend fun sendVerificationEmail(
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<Void, Void>(
        operation = { firebaseAuth.currentUser?.sendEmailVerification()!! },
        parse = { it }, errorLiveData
    )

    fun isEmailEdited(email: String): Boolean {
        return firebaseAuth.currentUser?.email != email
    }

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

    fun reloadAuthIfEmailNotVerified() {
        isEmailVerified()?.let {
            firebaseAuth.currentUser?.reload()
        }
    }

}
