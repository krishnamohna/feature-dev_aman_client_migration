package com.cardio.doctor.network

data class NetworkError(val msg: String="Something went wrong") : Exception(msg)
