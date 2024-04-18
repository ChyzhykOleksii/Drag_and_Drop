package com.conscensia.draganddrop.data.sourse

import android.content.Context
import com.google.gson.Gson

class SharedPreferencesDataSource(context: Context) : ISharedPreferencesDataSource {

    companion object {
        private const val SHARED_PREF_NAME = "sharedPref"
    }

    val sharedPref = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

    override fun saveData(key: String, value: String) {
        sharedPref.edit().putString(key, value).apply()
    }

    override fun getData(key: String): String {
        return sharedPref.getString(key, "") ?: ""
    }

    override fun removeData(key: String) {
        sharedPref.edit().remove(key).apply()
    }
}