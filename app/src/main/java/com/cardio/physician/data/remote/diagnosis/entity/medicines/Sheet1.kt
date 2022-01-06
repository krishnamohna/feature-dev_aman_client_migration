package com.cardio.physician.data.remote.diagnosis.entity.medicines

class Sheet1 {
    val category: String = ""
    val diuretics: String = ""
    val rateControlAgent: String = ""
    val name: String = ""
        get() {
            return field.capitalize()
        }
    val searchKey: String
        get() {
            return name.toLowerCase()
        }
    val tradeName: String = ""
}
