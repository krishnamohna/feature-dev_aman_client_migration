package com.cardio.physician.ui.common.utils

import androidx.lifecycle.MutableLiveData
import com.cardio.physician.network.Resource
import com.google.android.gms.tasks.Task
import kotlinx.coroutines.tasks.await

sealed class Response<T>() {
    data class Failure<T>(val exception: Exception) : Response<T>()
    data class Success<T>(val data: T) : Response<T>()

}

suspend fun <T> firebaseTakeOff(operation: Task<T>, liveData : MutableLiveData<Resource<Exception>>): T? {
    return try {
        operation.await()
    } catch (e: Exception) {
        liveData.postValue(Resource.firebaseException(e))
        null
       /* if (networkHelper.isNetworkConnected()) {
            Resource.requiredResource("", R.string.getting_some_error)
        } else {
            Resource.requiredResource("", R.string.err_no_network_available)
        }*/
    }
}

suspend inline fun <T, R> firebaseQuery(
    crossinline operation: () -> Task<T>,
    crossinline parse: (T) -> R,
    errorLiveData: MutableLiveData<Resource<Exception>>,
): R? {
    return try {
        parse(operation().await())
    } catch (e: Exception) {
        errorLiveData.postValue(Resource.firebaseException(e))
        null
    }
}



suspend inline fun <T, R> firebaseDocumentQuery(
    crossinline operation: suspend () -> T,
    crossinline parse: suspend (T) -> R,
    errorLiveData: MutableLiveData<Resource<Exception>>,
): R? {
    return try {
        parse(operation())
    } catch (e: Exception) {
        errorLiveData.postValue(Resource.firebaseException(e))
        null
    }
}