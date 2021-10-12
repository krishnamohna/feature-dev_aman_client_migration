package com.cardio.doctor.domain.fitness

import com.cardio.doctor.domain.common.model.UserModel

interface FitnessRepositary {
    fun getProfileData(): UserModel
    fun login()
    fun logout()
}