package com.cardio.doctor.data.local

import com.cardio.doctor.ui.common.utils.Preference.Companion.IS_TUTORIAL_SHOWN
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserManager @Inject constructor(
    private val sharedPreferences: SharedPreferences
) {
    val isTutorialRequired: Boolean
        get() = sharedPreferences.getBoolean(IS_TUTORIAL_SHOWN)

    fun setBoolean(key: String , value : Boolean){
        sharedPreferences.setBoolean(key, value)
    }

    fun setString(key: String , value : String){
        sharedPreferences.setString(key, value)
    }

    fun getString(key: String) : String{
        return sharedPreferences.getString(key)
    }

    fun clearAllPreference(){
        sharedPreferences.clearSharedPreference()
    }
}
