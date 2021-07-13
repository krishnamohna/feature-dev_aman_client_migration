package com.cardio.doctor.utils.network

import android.util.Log
import com.cardio.doctor.R
import com.cardio.doctor.model.BaseResponse
import com.google.gson.Gson
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withTimeout
import retrofit2.HttpException
import retrofit2.Response

val GSON = Gson()

@Suppress("BlockingMethodInNonBlockingContext")
inline fun <reified ResponseType : BaseResponse> networkCall(
    apiConstant: String,
    timeout: Long = 10000L, // 10 sec
    showLoader: Boolean = true,
    crossinline fetch: suspend () -> Response<ResponseType>
) = flow<Resource<ResponseType>> {
    try {
        if (showLoader) {
            emit(Resource.loading(apiConstant, null))
        }
        Log.d("TAG", "networkCall() called with loading")
        withTimeout(timeout) {
            val response: Response<ResponseType> = fetch()
            if (response.isSuccessful) {
                emit(Resource.success(apiConstant, response.body()))
                Log.d("TAG", "networkCall() called with success if part")
            } else {
                emit(parseErrorResponse(apiConstant, response.errorBody()?.string()))
                Log.d("TAG", "networkCall() called with success else part")
            }
        }
    } catch (exception: TimeoutCancellationException) {
        emit(Resource.requiredResource(apiConstant, R.string.err_no_network_available))
        Log.d("TAG", "networkCall() called with error $exception")
    } catch (exception: HttpException) {
        emit(parseErrorResponse(apiConstant, exception.response()?.errorBody()?.string()))
        Log.d("TAG", "networkCall() called with error $exception")
    } catch (e: NoNetworkException) {
        emit(Resource.requiredResource(apiConstant, R.string.err_no_network_available))
        Log.d("TAG", "networkCall() called with error $e")
    }  catch (e: UserBlockedException) {
        emit(Resource.requiredResource(apiConstant, R.string.err_empty_to_identifying_user_blocked))
        Log.d("TAG", "networkCall() called with error $e")
    }catch (throwable: Throwable) {
        emit(Resource.requiredResource(apiConstant, R.string.err_no_network_available))
        Log.d("TAG", "networkCall() called with error $throwable")
    }
}

inline fun <reified ResponseType : BaseResponse> parseErrorResponse(
    apiConstant: String,
    errorBodyAsString: String?
): Resource<ResponseType> {
    return try {
        val response = GSON.fromJson(errorBodyAsString, ResponseType::class.java)
        Resource.error(apiConstant, response.statusCode, response.message, response)
    } catch (e: Exception) {
        Resource.error(apiConstant, -1, e.message ?: e.localizedMessage ?: "", null)
    }
}


