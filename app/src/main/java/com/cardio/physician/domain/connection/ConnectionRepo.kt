package com.cardio.physician.domain.connection

interface ConnectionRepo {
    suspend fun getMyConnections(): List<ConnectionModel>
}