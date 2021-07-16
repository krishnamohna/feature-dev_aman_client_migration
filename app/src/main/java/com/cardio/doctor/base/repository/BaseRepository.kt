package com.cardio.doctor.base.repository

import com.cardio.doctor.api.ApiService
import javax.inject.Inject

open class BaseRepository @Inject constructor( val apiService: ApiService) {

}
