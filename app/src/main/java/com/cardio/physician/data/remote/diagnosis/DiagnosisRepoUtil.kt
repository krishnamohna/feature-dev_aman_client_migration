package com.cardio.physician.data.remote.diagnosis

import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.ui.common.utils.extentions.isAllDigits

class DiagnosisRepoUtil {

     fun getHealthLogMap(diagnosisModel: DiagnosisModel): MutableMap<String, Any?> {
        val fitnessModel = FitnessModel()
        diagnosisModel.weight?.let { weight ->
            if (weight.isNotEmpty()) fitnessModel.weight = weight
        }
        diagnosisModel.heartRate?.let { heartRate ->
            if (heartRate.isNotEmpty()) fitnessModel.heartRate = heartRate
        }
        diagnosisModel.topBp?.let { topBp ->
            if (topBp.isNotEmpty()) fitnessModel.bloodPressureTopBp = topBp
        }
        diagnosisModel.bottomBp?.let { bottomBp ->
            if (bottomBp.isNotEmpty()) fitnessModel.bloodPressureBottomBp = bottomBp
        }
        diagnosisModel.stepCount?.let { stepCount ->
            if (stepCount.isNotEmpty()) fitnessModel.stepCount = stepCount
        }
        diagnosisModel.date?.let { date -> if (date.isNotEmpty()) fitnessModel.date = date }
        diagnosisModel.timeStamp?.let { timeStamp -> fitnessModel.timeStamp = timeStamp }
        //create map from fitnessmodel
        val mapHealth = mutableMapOf<String, Any?>()
        fitnessModel.weight?.let { mapHealth.put(FireStoreDocKey.WEIGHT, it) }
        fitnessModel.heartRate?.let { mapHealth.put(FireStoreDocKey.HEART_RATE, it) }
        fitnessModel.bloodPressureTopBp?.let {
            mapHealth.put(
                FireStoreDocKey.BLOOD_SYSTOLIC_BP,
                it
            )
        }
        fitnessModel.timeStamp?.let { mapHealth.put(FireStoreDocKey.TIME_STAMP, it) }
        fitnessModel.date?.let { mapHealth.put(FireStoreDocKey.DATE, it) }
        fitnessModel.bloodPressureBottomBp?.let {
            mapHealth.put(
                FireStoreDocKey.BLOOD_DIASTOLIC_BP,
                it
            )
        }
        fitnessModel.stepCount?.let { mapHealth.put(FireStoreDocKey.STEP_COUNT, it) }
        return mapHealth
    }

     fun getUserModelMap(diagnosisModel: DiagnosisModel): MutableMap<String, Any?> {
        val userModel = UserModel.Builder.apply {
            firstName = diagnosisModel.firstName
            lastName = diagnosisModel.lastName
            if (diagnosisModel.age != null && !diagnosisModel.age!!.isAllDigits())
                dob = diagnosisModel.age
            weight = diagnosisModel.weight
        }.build()
        val mapUserProfile = mutableMapOf<String, Any?>()
        userModel.weight?.let { mapUserProfile.put(FireStoreDocKey.WEIGHT, it) }
        userModel.dob?.let { mapUserProfile.put(FireStoreDocKey.DOB_PROFILE, it) }
        userModel.firstName?.let { mapUserProfile.put(FireStoreDocKey.FIRST_NAME, it) }
        userModel.lastName?.let { mapUserProfile.put(FireStoreDocKey.LAST_NAME, it) }
        return mapUserProfile
    }

}