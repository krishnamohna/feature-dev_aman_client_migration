package com.cardio.doctor.data.remote.diagnosis.repositary

import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.DiagnosisRepo
import com.cardio.doctor.domain.diagnosis.MedicineModel
import com.cardio.doctor.network.NetworkError
import com.cardio.doctor.network.api.ApiService
import javax.inject.Inject


class DiagnosisRepoImp @Inject constructor(val apiService: ApiService) : DiagnosisRepo {

    override suspend fun searchMedicine(name: String): BaseModel<MedicineModel> {
        return apiService.searchMedicine(name).execute().run {
            body()?.let {
                return it.toModel()
            }
            return throw NetworkError()
        }
    }


}
