package com.cardio.physician.network

data class NetworkError(val errorCode:Int,val msg: String) : Exception(msg)
