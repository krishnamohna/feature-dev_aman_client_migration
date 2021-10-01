package com.cardio.doctor.domain.common.repository

import com.cardio.doctor.network.Resource
import com.cardio.doctor.ui.common.utils.livedata.SingleLiveEvent

interface UserAuthRepositary {
    suspend fun updateEmailAddress(
        newEmail: String,
        oldPass: String,
        _firebaseException: SingleLiveEvent<Resource<Exception>>
    ): Boolean
}