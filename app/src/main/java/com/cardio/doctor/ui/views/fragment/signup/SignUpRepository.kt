package com.cardio.doctor.ui.views.fragment.signup

import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.network.api.ApiService
import com.cardio.doctor.domain.common.repository.BaseRepository
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.utils.firebaseQuery
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.SignInMethodQueryResult
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import java.util.*
import javax.inject.Inject

class SignUpRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(firebaseAuth, fireStore, storageReference, apiService) {

    /* suspend fun isEmailAlreadyExist(email: String): Boolean {
         try {
             val result = firebaseAuth.fetchSignInMethodsForEmail(email).await()
             if (result?.signInMethods?.size!! > 0) {
                 return true
             }
         } catch (e: Exception) {
         }
         return false
     }
 */
    suspend fun isEmailAlreadyExist(
        email: String,
        errorLiveData: MutableLiveData<Resource<Exception>>,
    ) = firebaseQuery<SignInMethodQueryResult, Boolean>(
        operation = { firebaseAuth.fetchSignInMethodsForEmail(email) },
        parse = { result ->
            return@firebaseQuery result.signInMethods?.size ?: 0 > 0
        }, errorLiveData
    )


    /* suspend fun uploadImageOnFirebaseStorage(fileUri: Uri?, fileName: String): Uri? {
         return try {
             val ref = storageReference.child("images/" + fileName)
             ref.putFile(fileUri!!).await()
             val imageUrl = ref.downloadUrl.await()
             imageUrl
         } catch (e: Exception) {
             null
         }
     }*/


}
