package com.cardio.physician.ui.common.utils.extentions

import android.os.Parcel
import com.cardio.physician.domain.common.model.UserModel
import com.cardio.physician.domain.connection.ConnectionModel
import com.cardio.physician.domain.diagnosis.DiagnosisModel
import com.cardio.physician.domain.diagnosis.MedicineModel
import com.cardio.physician.domain.questionare.model.QuestionModel
import com.cardio.physician.domain.user.SignUpUserType
import com.cardio.physician.domain.user.UserType
import com.cardio.physician.ui.common.utils.FireStoreDocKey
import com.cardio.physician.domain.addpatient.PatientModel
import com.cardio.physician.domain.fitness.model.FitnessModel
import com.cardio.physician.domain.notifications.NotificationModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.getField
import com.google.gson.Gson


fun GoogleSignInAccount.toUserModel(): UserModel {
    var firstName = displayName.split(" ")?.firstOrNull() ?: ""
    var lastname = displayName.split(" ")?.let {
        if (it.size > 1)
            it[1]
        else
            ""
    }
    return UserModel("",
        firstName,
        lastname,
        null,
        null,
        email,
        photoUrl.toString(),
        SignUpUserType.GOOGLE)
}

fun DocumentSnapshot.toUserModel(): UserModel {
    var uid = data?.get(FireStoreDocKey.USER_ID) as? String?
    val firstName = data?.get(FireStoreDocKey.FIRST_NAME) as? String?
    val lastName = data?.get(FireStoreDocKey.LAST_NAME) as? String?
    val email = data?.get(FireStoreDocKey.EMAIL) as? String?
    val countryCode = data?.get(FireStoreDocKey.COUNTRY_CODE) as? String?
    val phoneNumber = data?.get(FireStoreDocKey.PHONE_NUMBER) as? String?
    val gender = data?.get(FireStoreDocKey.GENDER) as? String?
    val dob = data?.get(FireStoreDocKey.DOB_PROFILE) as? String?
    val height = data?.get(FireStoreDocKey.HEIGHT) as? String?
    val weight = data?.get(FireStoreDocKey.WEIGHT) as? String?
    val imageUrl = data?.get(FireStoreDocKey.IMAGE_URL) as? String?
    val signUpUserType = data?.get(FireStoreDocKey.SIGN_UP_TYPE) as? String?
    val userType = data?.get(FireStoreDocKey.USER_TYPE) as? String?
    return UserModel(uid,
        firstName,
        lastName,
        countryCode,
        phoneNumber,
        email,
        imageUrl,
        SignUpUserType.fromName(signUpUserType),
        gender,
        height,
        weight,
        dob,
        UserType.fromName(userType)
    )
}

fun QuerySnapshot.toQuestionModel(): List<QuestionModel> {
    var list = mutableListOf<QuestionModel>()
    documents.forEach {
        var question = it.get(FireStoreDocKey.QUESTION_KEY) as? String
        var type = it.get(FireStoreDocKey.TYPE) as? Long
        var id = it.get(FireStoreDocKey.ID) as? String
        var option_1 = it.get(FireStoreDocKey.OPTION_1) as? String
        var option_2 = it.get(FireStoreDocKey.OPTION_2) as? String
        var option_3 = it.get(FireStoreDocKey.OPTION_3) as? String
        var option_4 = it.get(FireStoreDocKey.OPTION_4) as? String
        var position = it.get(FireStoreDocKey.POSITION) as? Long
        var secondary_option_1 = it.get(FireStoreDocKey.SECONDARY_OPTION_1) as? String
        var secondary_option_2 = it.get(FireStoreDocKey.SECONDARY_OPTION_2) as? String
        var secondary_option_3 = it.get(FireStoreDocKey.SECONDARY_OPTION_3) as? String
        list.add(QuestionModel(id,type, question,option_1,option_2,option_3,option_4,position,secondary_option_1,secondary_option_2,secondary_option_3))
    }
    return list
}

fun QuerySnapshot.toDiagnosisModel(): List<DiagnosisModel> {
    var list = mutableListOf<DiagnosisModel>()
    val gson=Gson()
    this.forEach {
       var diagnosisModel=gson.fromJson(gson.toJson(it.data), DiagnosisModel::class.java)
        diagnosisModel.documentId=it.id
        list.add(diagnosisModel)
    }
    return list
}

 fun QuerySnapshot.toMedicineListModel(): List<MedicineModel> {
    var list = mutableListOf<MedicineModel>()
    this.forEach {
        var medicineModel=MedicineModel(Parcel.obtain())
        medicineModel.name=it.get(FireStoreDocKey.NAME) as? String
        medicineModel.category=it.getField(FireStoreDocKey.CATEGORY)
        medicineModel.dosage=it.getField(FireStoreDocKey.DOSAGE)
        medicineModel.diuretics=it.getField(FireStoreDocKey.IS_DIURETICS)
        medicineModel.tradeName=it.getField(FireStoreDocKey.TRADE_NAME)
        medicineModel.rateControlAgent=it.getField(FireStoreDocKey.RATE_CONTROL_AGENT)
        list.add(medicineModel)
    }
    return list
}

fun DocumentSnapshot.toMedicineModel(medicineModel: MedicineModel) {
    medicineModel.name=get(FireStoreDocKey.NAME) as? String
    medicineModel.category=getField(FireStoreDocKey.CATEGORY)
    medicineModel.dosage=getField(FireStoreDocKey.DOSAGE)
    medicineModel.diuretics=getField(FireStoreDocKey.IS_DIURETICS)
    medicineModel.tradeName=getField(FireStoreDocKey.TRADE_NAME)
    medicineModel.rateControlAgent=getField(FireStoreDocKey.RATE_CONTROL_AGENT)
}

fun DocumentSnapshot.toMedicineModel(): MedicineModel {
    var medicineModel=MedicineModel(Parcel.obtain())
    toMedicineModel(medicineModel)
    return medicineModel
}


fun QuerySnapshot.toPatientModel(): List<PatientModel> {
    var list = mutableListOf<PatientModel>()
    documents.forEach {
        val userId = it.get(FireStoreDocKey.USER_ID) as? String?
        val firstName = it.get(FireStoreDocKey.FIRST_NAME) as? String?
        val lastName = it.get(FireStoreDocKey.LAST_NAME) as? String?
        val imageUrl = it.get(FireStoreDocKey.IMAGE_URL) as? String?
        val email = it.get(FireStoreDocKey.EMAIL) as? String?
        list.add(PatientModel(userId, firstName, lastName, imageUrl, email))
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
        val email = it.get(FireStoreDocKey.EMAIL) as? String?
        val patientModel = PatientModel(userId, firstName, lastName, imageUrl, email)
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
        val timestamp = it.get(FireStoreDocKey.TIME_STAMP) as? Number?
        if(it.get(FireStoreDocKey.REQUEST_STATUS) as? Boolean? == true)
            list.add(ConnectionModel(userId, firstName, lastName, imageUrl, timestamp))
    }
    return list
}


fun DocumentSnapshot.toFitNessModel(): FitnessModel {
    var fitnessModel = FitnessModel()
    fitnessModel.weight = data?.get(FireStoreDocKey.WEIGHT) as? String?
    fitnessModel.heartRate = data?.get(FireStoreDocKey.HEART_RATE) as? String?
    fitnessModel.bloodPressureTopBp =
        data?.get(FireStoreDocKey.BLOOD_PRESURE) as? String?
    fitnessModel.date = data?.get(FireStoreDocKey.DATE) as? String?
    fitnessModel.timeStamp = data?.get(FireStoreDocKey.DATE) as? Long?
    fitnessModel.bloodPressureTopBp =
        data?.get(FireStoreDocKey.BLOOD_SYSTOLIC_BP) as? String?
    fitnessModel.bloodPressureBottomBp =
        data?.get(FireStoreDocKey.BLOOD_DIASTOLIC_BP) as? String?
    fitnessModel.stepCount =
        data?.get(FireStoreDocKey.STEP_COUNT) as? String?
    return fitnessModel
}

fun QuerySnapshot.toNotificationsList(): List<NotificationModel> {
    val listNotifications = mutableListOf<NotificationModel>()
    val gson = Gson()
    for (document in this) {
        var notificationModel = gson.fromJson(gson.toJson(document.data), NotificationModel::class.java)
        notificationModel.documentId=document.id
        notificationModel.documentSnapShot=document
        listNotifications.add(notificationModel)
    }
    return listNotifications
}