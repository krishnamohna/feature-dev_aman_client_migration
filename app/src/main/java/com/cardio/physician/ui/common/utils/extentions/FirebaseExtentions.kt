package com.cardio.physician.ui.common.utils.extentions

import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.common.model.UserType
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.gson.Gson


fun GoogleSignInAccount.toUserModel(): UserModel {
    return UserModel("", "", null, null, null, email, photoUrl.toString(), UserType.GOOGLE)
}

fun DocumentSnapshot.toUserModel(): UserModel {
    var uid = data?.get(FireStoreDocKey.USER_ID) as? String?
    val firstName = data?.get(FireStoreDocKey.FIRST_NAME) as? String?
    val lastName = data?.get(FireStoreDocKey.LAST_NAME) as? String?
    val email = data?.get(FireStoreDocKey.EMAIL) as? String?
    val countryCode = data?.get(FireStoreDocKey.COUNTRY_CODE) as? String?
    val phoneNumber = data?.get(FireStoreDocKey.PHONE_NUMBER) as? String?
    val gender = data?.get(FireStoreDocKey.GENDER) as? String?
    val dob = data?.get(FireStoreDocKey.DOB) as? String?
    val height = data?.get(FireStoreDocKey.HEIGHT) as? String?
    val weight = data?.get(FireStoreDocKey.WEIGHT) as? String?
    val imageUrl = data?.get(FireStoreDocKey.IMAGE_URL) as? String?
    val userType = data?.get(FireStoreDocKey.SIGN_UP_TYPE) as? String?
    return UserModel(uid,
        firstName,
        lastName,
        countryCode,
        phoneNumber,
        email,
        imageUrl,
        UserType.fromName(userType),
        gender,
        height,
        weight,
        dob)
}

fun QuerySnapshot.toQuestionModel(): List<QuestionModel> {
    var list = mutableListOf<QuestionModel>()
    documents.forEach {
        var question = it.get(FireStoreDocKey.QUESTION_KEY) as? String
        var type = it.get(FireStoreDocKey.TYPE) as? Long
        var option_1 = it.get(FireStoreDocKey.OPTION_1) as? String
        var option_2 = it.get(FireStoreDocKey.OPTION_2) as? String
        var option_3 = it.get(FireStoreDocKey.OPTION_3) as? String
        var option_4 = it.get(FireStoreDocKey.OPTION_4) as? String
        var position = it.get(FireStoreDocKey.POSITION) as? Long
        var secondary_option_1 = it.get(FireStoreDocKey.SECONDARY_OPTION_1) as? String
        var secondary_option_2 = it.get(FireStoreDocKey.SECONDARY_OPTION_2) as? String
        var secondary_option_3 = it.get(FireStoreDocKey.SECONDARY_OPTION_3) as? String
        list.add(QuestionModel(type, question,option_1,option_2,option_3,option_4,position,secondary_option_1,secondary_option_2,secondary_option_3))
    }
    return list
}

fun QuerySnapshot.toDiagnosisModel(): List<DiagnosisModel> {
    var list = mutableListOf<DiagnosisModel>()
    val gson=Gson()
    this.forEach {
       var diagnosisModel=gson.fromJson(gson.toJson(it.data), DiagnosisModel::class.java)
       /* diagnosisModel.firstName = it?.get(FireStoreDocKey.FIRST_NAME) as? String?
        diagnosisModel.lastName = it?.get(FireStoreDocKey.LAST_NAME) as? String?
        diagnosisModel.age = it?.get(FireStoreDocKey.AGE) as? String?
        diagnosisModel.weight =  it?.get(FireStoreDocKey.WEIGHT) as? String?
        diagnosisModel.heartRate = it?.get(FireStoreDocKey.HEART_RATE) as? String?
        diagnosisModel.topBp = it?.get(FireStoreDocKey.TOP_BP) as? String?
        diagnosisModel.bottomBp =it?.get(FireStoreDocKey.BOTTOM_BP) as? String?
        diagnosisModel.ailment =it?.get(FireStoreDocKey.AILMENT) as? String?
        diagnosisModel.date =it?.get(FireStoreDocKey.DATE) as? String?
        var listMedications=it?.get(FireStoreDocKey.MEDICATIONS) as ArrayList<HashMap<String,String>>*/
        list.add(diagnosisModel)
    }
    return list
}

fun QuerySnapshot.toPatientModel(): List<PatientModel> {
    var list = mutableListOf<PatientModel>()
    documents.forEach {
        val userId = it.get(FireStoreDocKey.USER_ID) as? String?
        val firstName = it.get(FireStoreDocKey.FIRST_NAME) as? String?
        val lastName = it.get(FireStoreDocKey.LAST_NAME) as? String?
        val imageUrl = it.get(FireStoreDocKey.IMAGE_URL) as? String?
        list.add(PatientModel(userId, firstName, lastName, imageUrl))
    }
    return list
}

fun QuerySnapshot.toCPatientModel(): List<PatientModel> {
    var list = mutableListOf<PatientModel>()
    documents.forEach {
        val userId = it.id as? String?
        val firstName = it.get(FireStoreDocKey.FIRST_NAME) as? String?
        val lastName = it.get(FireStoreDocKey.LAST_NAME) as? String?
        val imageUrl = it.get(FireStoreDocKey.IMAGE_URL) as? String?
        val patientModel = PatientModel(userId, firstName, lastName, imageUrl)
        patientModel.isAdded = if(it.get(FireStoreDocKey.REQUEST_STATUS) as? Boolean? == true) 1 else 2
        list.add(patientModel)
    }
    return list
}

fun QuerySnapshot.toConnectionModel(): List<ConnectionModel> {
    var list = mutableListOf<ConnectionModel>()
    documents.forEach {
        val userId = it.id as? String?
        val firstName = it.get(FireStoreDocKey.FIRST_NAME) as? String?
        val lastName = it.get(FireStoreDocKey.LAST_NAME) as? String?
        val imageUrl = it.get(FireStoreDocKey.IMAGE_URL) as? String?
        val timestamp = it.get(FireStoreDocKey.TIME_STAMP) as? Long?
        if(it.get(FireStoreDocKey.REQUEST_STATUS) as? Boolean? == true)
        list.add(ConnectionModel(userId, firstName, lastName, imageUrl, timestamp))
    }
    return list
}