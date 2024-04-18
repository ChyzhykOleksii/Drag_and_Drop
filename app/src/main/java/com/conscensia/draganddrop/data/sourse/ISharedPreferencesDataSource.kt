package com.conscensia.draganddrop.data.sourse

interface ISharedPreferencesDataSource {
    fun saveData(key: String, value: String)
    fun getData(key: String): String
    fun removeData(key: String)
}