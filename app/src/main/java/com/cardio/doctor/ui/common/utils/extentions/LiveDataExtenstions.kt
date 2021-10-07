package com.cardio.doctor.ui.common.utils.extentions

import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.MedicineModel
import com.cardio.doctor.network.Resource
import kotlinx.coroutines.CoroutineScope


fun <T> MutableLiveData<Resource<T>>.setLoading() = postValue(Resource.setLoading())
fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) = postValue(Resource.success("", data))
fun <T> MutableLiveData<Resource<T>>.setError(networkException: Exception) = postValue(Resource.error("",1,networkException.localizedMessage,null))

fun CoroutineScope.observeApi(
    searchMedicine: BaseModel<MedicineModel>,
    mutableMedicineData: MutableLiveData<Resource<MedicineModel>>
) {
    searchMedicine.customObserver(mutableMedicineData)
}

fun <T> BaseModel<T>.customObserver(mutableMedicineData: MutableLiveData<Resource<T>>) {
    mutableMedicineData.setSuccess(data)
}

