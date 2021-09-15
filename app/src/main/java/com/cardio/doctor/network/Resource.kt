package com.cardio.doctor.network

import com.cardio.doctor.AppCardioPatient
import com.cardio.doctor.R
import com.cardio.doctor.api.ApiStatus.Companion.STATUS_200
import com.google.firebase.FirebaseNetworkException
import java.io.Serializable
import java.lang.Exception

data class Resource<out T>(val apiConstant: String?, val apiCode :Int, val status: Status, val data: T?,
                           var message: String?, val resourceId: Int?,val exception: Exception ?=null) : Serializable {

    companion object {
        fun <T> success(apiConstant : String, data: T?): Resource<T> {
            return Resource(apiConstant, STATUS_200, Status.SUCCESS, data, null,0)
        }

        fun <T> error(apiConstant : String, errorCode: Int,  msg: String,data: T?): Resource<T> {
            //we can send our messages by using errorCode
            when (errorCode) {
                404 -> {
                    //val message = "Detail Not found"
                    return Resource(apiConstant, errorCode, Status.ERROR, data, msg, 0)
                }
                in 400..499 -> {
                   // val message = "User unauthorized"
                    return Resource(apiConstant, errorCode, Status.ERROR, data, msg, 0)
                }
                in 500..600 -> {
                   // val message = "There seems some problem connecting to server.Please try again"
                    return Resource(apiConstant, errorCode, Status.ERROR, data, msg, 0)
                }
                else -> return Resource(apiConstant, errorCode, Status.ERROR, data, msg, 0)
            }
        }

        fun<T> requiredResource(apiConstant: String,resourceId: Int) : Resource<T> {
            return Resource(apiConstant,-1, Status.RESOURCE, null, "",resourceId)
        }

        fun<T> setAlpha(apiConstant: String,resourceId: Int) : Resource<T> {
            return Resource(apiConstant,-1, Status.ALPHA, null, "",resourceId)
        }

        fun <T> loading(apiConstant : String,data: T?): Resource<T> {
            return Resource(apiConstant,0, Status.LOADING, data, null,0)
        }

        fun <T> firebaseException(e : Exception): Resource<T> {
            var message = ""
            message = when(e){
                is FirebaseNetworkException ->{
                    "Please check your internet connection and try again later."
                }
                else ->{
                    e.localizedMessage
                        ?: "Please check your internet connection and try again later."
                }
            }

            return Resource("",-1, Status.ERROR, null, message,0,e)
        }
    }
}