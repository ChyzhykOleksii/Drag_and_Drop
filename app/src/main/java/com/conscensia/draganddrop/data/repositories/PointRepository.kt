package com.conscensia.draganddrop.data.repositories

import android.graphics.Point
import com.conscensia.draganddrop.data.sourse.ISharedPreferencesDataSource
import com.google.gson.Gson

class PointRepository(
    private val sharedPreferencesDataSource: ISharedPreferencesDataSource,
    private val gson: Gson
) : IPointRepository {

    companion object {
        private const val POINT_KEY = "point"
    }

    override fun savePoint(point: Point) {
        sharedPreferencesDataSource.saveData(POINT_KEY, gson.toJson(point))
    }

    override fun getPoint(): Point {
        return gson.fromJson(sharedPreferencesDataSource.getData(POINT_KEY), Point::class.java)
            ?: Point(0, 0)
    }

    override fun removePoint() {
        sharedPreferencesDataSource.removeData(POINT_KEY)
    }
}