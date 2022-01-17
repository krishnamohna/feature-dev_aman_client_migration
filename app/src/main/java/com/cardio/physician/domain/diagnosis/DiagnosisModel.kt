package com.cardio.physician.domain.diagnosis

import android.os.Parcel
import android.os.Parcelable
import com.cardio.physician.domain.questionare.model.QuestionModel
class DiagnosisModel() : Parcelable {
    var documentId: String? = null
    var date: String?=null
    var timeStamp: Long?=null
    var questionnaire: List<QuestionModel>?=null
    var ailment: String? = null
    var firstName: String? = null
    var lastName: String? = null
    var age: String? = null
    var weight: String? = null
    var heartRate: String? = null
    var topBp: String? = null
    var bottomBp: String? = null
    var stepCount: String? = null
    var mediciene: String? = null
    var medications: List<MedicineModel>? = null

    constructor(parcel: Parcel) : this() {
        documentId = parcel.readString()
        date = parcel.readString()
        timeStamp = parcel.readValue(Long::class.java.classLoader) as? Long
        questionnaire = parcel.createTypedArrayList(QuestionModel.CREATOR)
        ailment = parcel.readString()
        firstName = parcel.readString()
        lastName = parcel.readString()
        age = parcel.readString()
        weight = parcel.readString()
        heartRate = parcel.readString()
        topBp = parcel.readString()
        bottomBp = parcel.readString()
        stepCount = parcel.readString()
        mediciene = parcel.readString()
        medications = parcel.createTypedArrayList(MedicineModel.CREATOR)
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(documentId)
        parcel.writeString(date)
        parcel.writeValue(timeStamp)
        parcel.writeTypedList(questionnaire)
        parcel.writeString(ailment)
        parcel.writeString(firstName)
        parcel.writeString(lastName)
        parcel.writeString(age)
        parcel.writeString(weight)
        parcel.writeString(heartRate)
        parcel.writeString(topBp)
        parcel.writeString(bottomBp)
        parcel.writeString(stepCount)
        parcel.writeString(mediciene)
        parcel.writeTypedList(medications)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<DiagnosisModel> {
        override fun createFromParcel(parcel: Parcel): DiagnosisModel {
            return DiagnosisModel(parcel)
        }

        override fun newArray(size: Int): Array<DiagnosisModel?> {
            return arrayOfNulls(size)
        }
    }

}
