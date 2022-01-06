package com.cardio.physician.data.remote.common.repositary

import com.cardio.physician.domain.common.repository.UserAuthRepositary
import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class UserAuthRepositaryImp @Inject constructor(val firebaseAuth: FirebaseAuth) :
    UserAuthRepositary {

    override suspend fun updateEmailAddress(
        newEmail: String,
        oldPass: String,
        _firebaseException: SingleLiveEvent<Resource<Exception>>
    ): Boolean {
        try {
            var firebaseUser = firebaseAuth.currentUser
            val credential = EmailAuthProvider.getCredential(firebaseUser?.email ?: "", oldPass)
            firebaseUser?.reauthenticate(credential)?.await()
            firebaseUser?.updateEmail(newEmail)?.await()
            //send verification email now
            firebaseAuth.currentUser?.sendEmailVerification()!!
            return true
        }catch (e:java.lang.Exception){
            _firebaseException.postValue(Resource.firebaseException(e))
            return false
        }
    }

    override fun getUserCreatedTime(): Long? {
       return firebaseAuth.currentUser?.metadata?.creationTimestamp
    }


}