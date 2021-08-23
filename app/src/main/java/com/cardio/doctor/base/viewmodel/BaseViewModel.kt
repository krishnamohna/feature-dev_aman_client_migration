package com.cardio.doctor.base.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.navigation.NavDirections
import com.cardio.doctor.storage.UserManager
import com.cardio.doctor.base.repository.BaseRepository
import com.cardio.doctor.utils.livedata.SingleLiveEvent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val userManager: UserManager,
    private val baseRepository: BaseRepository,
    application :Application
) : AndroidViewModel(application){

    protected val auth: FirebaseAuth = Firebase.auth
    protected val db = Firebase.firestore

    private val _navDirectionLiveData = SingleLiveEvent<NavDirections>()
    val navDirectionLiveData: LiveData<NavDirections> = _navDirectionLiveData

    fun setDirection(navDirections: NavDirections) {
        _navDirectionLiveData.value = navDirections
    }

}



