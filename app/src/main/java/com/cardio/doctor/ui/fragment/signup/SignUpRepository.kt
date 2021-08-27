package com.cardio.doctor.ui.fragment.signup

import android.net.Uri
import android.util.Log
import android.widget.Toast
import com.cardio.doctor.api.ApiService
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.utils.FireStoreCollection
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask
import kotlinx.coroutines.tasks.await
import java.util.*
import javax.inject.Inject
import kotlin.collections.HashMap

class SignUpRepository @Inject constructor(firebaseAuth : FirebaseAuth ,
                                           private val fireStore : FirebaseFirestore,
                                           private val storageReference: StorageReference,
                                           apiService: ApiService
) : BaseRepository(firebaseAuth,fireStore,storageReference,apiService){

    suspend fun storeUserDataInFireStore(childName: String, hashMap: HashMap<String, Any>) : Boolean{
        return try{
            fireStore.collection(FireStoreCollection.USERS)
                .document()
                .set(hashMap)
                .await()
            true
        }catch (e : Exception){
            false
        }
    }

    suspend fun isEmailAlreadyExist(email: String): Boolean {
        try {
            val result = firebaseAuth.fetchSignInMethodsForEmail(email).await()
            if (result?.signInMethods?.size!! > 0) {
                return true
            }
        } catch (e: Exception) {
        }
        return false
    }

    suspend fun uploadImageOnFirebaseStorage(fileUri: Uri?, fileName : String): Uri? {
        return try{
            val ref = storageReference.child("images/" + fileName)
            ref.putFile(fileUri!!).await()
            val imageUrl = ref.downloadUrl.await()
            imageUrl
        }catch (e : Exception){
            null
        }
    }
}

