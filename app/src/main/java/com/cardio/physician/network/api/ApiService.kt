package com.cardio.physician.network.api

import com.cardio.physician.data.remote.diagnosis.entity.medicinesearch.MedicineEntity
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
   /* @GET(Commands.COMMAND_SEARCH_DRUG)
    suspend fun searchMedicine(@Query("name") name: String): Response<MedicineEntity>*/

    @GET(Commands.COMMAND_SEARCH_DRUG)
    suspend fun getMedicineDosages(@Query("name") name: String): Response<MedicineEntity>
}