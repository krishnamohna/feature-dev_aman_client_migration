package com.cardio.doctor.network.api

import com.cardio.doctor.data.remote.diagnosis.entity.MedicineEntity
import retrofit2.Call
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST(Commands.COMMAND_SEARCH_DRUG)
    suspend fun searchMedicine(@Query("name") name: String): Call<MedicineEntity>
}