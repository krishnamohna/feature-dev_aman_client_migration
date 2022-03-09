package com.cardio.physician.domain.diagnosis

import android.os.Parcel
import android.os.Parcelable

class MedicineModel(
    var name: String?,
    @Transient var searchedMed: String?,
    var category: String? = null,
    var dosage: String? = null,
    var diuretics: String? = null,
    var tradeName: String? = null,
    var rateControlAgent: String? = null,
    var listDosage: List<String>? = null,
    var noOfTime: String? = null,
    var perDayOrWeek: String? = null,
    var noOfTablets: String? = null
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
        parcel.createStringArrayList(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(searchedMed)
        parcel.writeString(category)
        parcel.writeString(dosage)
        parcel.writeString(diuretics)
        parcel.writeString(tradeName)
        parcel.writeString(rateControlAgent)
        parcel.writeStringList(listDosage)
        parcel.writeString(noOfTime)
        parcel.writeString(perDayOrWeek)
        parcel.writeString(noOfTablets)
    }

    override fun describeContents(): Int {
        return 0
    }

    fun hasDosage(): Boolean = !listDosage.isNullOrEmpty()

    companion object CREATOR : Parcelable.Creator<MedicineModel> {
        override fun createFromParcel(parcel: Parcel): MedicineModel {
            return MedicineModel(parcel)
        }

        override fun newArray(size: Int): Array<MedicineModel?> {
            return arrayOfNulls(size)
        }
    }

}