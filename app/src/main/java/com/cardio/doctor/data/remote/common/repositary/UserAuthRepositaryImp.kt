package com.cardio.doctor.data.remote.common.repositary

import com.cardio.doctor.domain.common.repository.UserAuthRepositary
import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent
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