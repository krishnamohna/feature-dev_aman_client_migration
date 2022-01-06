package com.cardio.physician.domain.common.repository

import com.cardio.physician.network.Resource
import com.cardio.physician.ui.common.utils.livedata.SingleLiveEvent

interface UserAuthRepositary {
    suspend fun updateEmailAddress(
        newEmail: String,
        oldPass: String,
        _firebaseException: SingleLiveEvent<Resource<Exception>>
    ): Boolean

    fun getUserCreatedTime():Long?

}