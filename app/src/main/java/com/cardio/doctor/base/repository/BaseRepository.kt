package com.cardio.doctor.base.repository

import com.cardio.doctor.api.ApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import javax.inject.Inject

open class BaseRepository @Inject constructor(
    val firebaseAuth: FirebaseAuth, fireStore: FirebaseFirestore, apiService: ApiService,
) {

}
