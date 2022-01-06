package com.cardio.physician.domain.questionare.model

import android.os.Parcel
import android.os.Parcelable

data class QuestionModel(
    val id:String?,
    val type: Long?,
    val question: String?,
    val option_1: String?,
    val option_2: String?,
    val option_3: String?,
    val option_4: String?,
    val position: Long?,
    val secondary_option_1: String?,
    val secondary_option_2: String?,
    val secondary_option_3: String?,
    var answer:String?=null,
    var answerSecondary:String?=null
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Long::class.java.classLoader) as? Long,
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeValue(type)
        parcel.writeString(question)
        parcel.writeString(option_1)
        parcel.writeString(option_2)
        parcel.writeString(option_3)
        parcel.writeString(option_4)
        parcel.writeValue(position)
        parcel.writeString(secondary_option_1)
        parcel.writeString(secondary_option_2)
        parcel.writeString(secondary_option_3)
        parcel.writeString(answer)
        parcel.writeString(answerSecondary)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<QuestionModel> {
        override fun createFromParcel(parcel: Parcel): QuestionModel {
            return QuestionModel(parcel)
        }

        override fun newArray(size: Int): Array<QuestionModel?> {
            return arrayOfNulls(size)
        }
    }

}