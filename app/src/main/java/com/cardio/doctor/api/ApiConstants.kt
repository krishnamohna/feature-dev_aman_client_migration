@file:JvmName("ApiConstants")
@file:JvmMultifileClass

package com.cardio.doctor.api

interface API_HEADER{
    companion object {
        const val CONTENT_TYPE = "Content-Type"
        const val ACCEPT="accept"
        const val APPLICATION_JSON="application/json"
        const val PLATFORM="platform"
        const val AUTHORIZATION="authorization"
        const val BEARER="Bearer "
        const val PLATFORM_TYPE="1"
    }
}

interface API_STATUS{
    companion object {
        const val STATUS_200=200
        const val STATUS_201=201
        const val STATUS_400=400
        const val STATUS_403=403
        const val STATUS_404=400
        const val STATUS_500=500
    }
}



