package com.cardio.physician.data.local

import android.content.Context
import android.content.SharedPreferences
import com.cardio.physician.ui.common.utils.Preference
import com.google.gson.GsonBuilder
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class SharedPreferences @Inject constructor(@ApplicationContext context: Context) {

    val sharedPreferences: SharedPreferences = context.getSharedPreferences(Preference.APP, Context.MODE_PRIVATE)

    fun setString(key: String, value: String?) {
        with(sharedPreferences.edit()) {
            putString(key, value)
            apply()
        }
    }

    fun getString(key: String): String {
        return sharedPreferences.getString(key, "")!!
    }

    fun getString(key: String,defValue:String?): String {
        return sharedPreferences.getString(key, defValue)!!
    }

    fun setBoolean(key: String, value: Boolean) {
        with(sharedPreferences.edit()) {
            putBoolean(key, value)
            apply()
        }
    }
    fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    fun <T> put(key: String,`object`: T) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        sharedPreferences.edit().putString(key, jsonString).apply()
    }

    inline fun <reified T> get(key: String): T? {
        val value = sharedPreferences.getString(key, "")
        return GsonBuilder().create().fromJson(value, T::class.java)
    }

    fun clearSharedPreference() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
    }
}
