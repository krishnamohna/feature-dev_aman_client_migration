package com.cardio.doctor.ui.views.fragment.profile.setting

import com.cardio.doctor.domain.common.repository.BaseRepository
import com.cardio.doctor.network.api.ApiService
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.StorageReference
import javax.inject.Inject

class SettingRepository @Inject constructor(
    firebaseAuth: FirebaseAuth,
    private val fireStore: FirebaseFirestore,
    private val storageReference: StorageReference,
    apiService: ApiService,
) : BaseRepository(
    firebaseAuth, fireStore, storageReference, apiService
) {


}
