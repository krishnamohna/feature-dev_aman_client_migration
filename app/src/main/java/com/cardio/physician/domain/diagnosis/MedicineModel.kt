package com.cardio.physician.domain.diagnosis

import android.os.Parcel
import android.os.Parcelable

data class MedicineModel(
    @Transient val drugGroupName: String?,
    var name: String?,
    @Transient val searchedMed: String?,
    var category: String?=null,
    var dosage: String?=null,
    var diuretics: String?=null,
    var tradeName: String?=null,
    var rateControlAgent: String?=null,
) :
    Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(drugGroupName)
        parcel.writeString(name)
        parcel.writeString(searchedMed)
        parcel.writeString(category)
        parcel.writeString(dosage)
        parcel.writeString(diuretics)
        parcel.writeString(tradeName)
        parcel.writeString(rateControlAgent)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<MedicineModel> {
        override fun createFromParcel(parcel: Parcel): MedicineModel {
            return MedicineModel(parcel)
        }

        override fun newArray(size: Int): Array<MedicineModel?> {
            return arrayOfNulls(size)
        }
    }

}