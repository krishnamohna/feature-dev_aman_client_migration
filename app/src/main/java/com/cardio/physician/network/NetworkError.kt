package com.cardio.physician.network

import com.cardio.physician.network.api.ApiStatus

data class NetworkError(val errorCode: Int, val msg: String) : Exception(msg) {
    companion object{
        fun noRecordFound(msg: String?=null): NetworkError {
            return NetworkError(ApiStatus.STATUS_404,msg?:"No Record Found !!")
        }
        fun noMoreRecordFound(): NetworkError {
            return noRecordFound("No More Record Found")
        }
    }
}