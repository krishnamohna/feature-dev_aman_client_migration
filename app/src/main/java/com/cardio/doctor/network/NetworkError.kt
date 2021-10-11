package com.cardio.doctor.network

data class NetworkError(val errorCode:Int,val msg: String) : Exception(msg)
