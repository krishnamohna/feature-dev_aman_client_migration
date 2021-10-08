package com.cardio.doctor.network.api

import com.cardio.doctor.data.remote.diagnosis.entity.MedicineEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET(Commands.COMMAND_SEARCH_DRUG)
    suspend fun searchMedicine(@Query("name") name: String): Response<MedicineEntity>
}