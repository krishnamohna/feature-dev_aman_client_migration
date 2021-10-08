package com.cardio.doctor.ui.common.utils.extentions

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cardio.doctor.domain.common.model.BaseModel
import com.cardio.doctor.domain.diagnosis.MedicineModel
import com.cardio.doctor.network.Resource
import com.cardio.doctor.network.Status
import com.cardio.doctor.network.api.ApiStatus.Companion.STATUS_500
import kotlinx.coroutines.CoroutineScope


fun <T> MutableLiveData<Resource<T>>.setLoading() = postValue(Resource.setLoading())
fun <T> MutableLiveData<Resource<T>>.setSuccess(data: T) = postValue(Resource.success("", data))
fun <T> MutableLiveData<Resource<T>>.setError(networkException: Exception) =
    postValue(Resource.error("", STATUS_500, networkException.localizedMessage, null))

fun CoroutineScope.observeApi(
    searchMedicine: BaseModel<MedicineModel>,
    mutableMedicineData: MutableLiveData<Resource<MedicineModel>>
) {
    mutableMedicineData.setSuccess(searchMedicine.data)
}

fun <T> LiveData<Resource<T>>.customObserver(
    viewLifecycleOwner: LifecycleOwner,
    onLoading: (showProgress: Boolean) -> Unit,
    onSuccess: (T?) -> Unit,
    onError: (String?) -> Unit
) {
    this.observe(viewLifecycleOwner, {
        when (it.status) {
            Status.LOADING -> {
                onLoading.invoke(true)
            }
            Status.SUCCESS -> {
                onLoading.invoke(false)
                onSuccess.invoke(it.data)
            }
            Status.ERROR -> {
                onLoading.invoke(false)
                onError.invoke(it.message)
            }
        }
    })
}


