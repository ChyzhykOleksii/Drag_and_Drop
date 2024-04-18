package com.conscensia.draganddrop.data.repositories

import android.graphics.Point

interface IPointRepository {
    fun savePoint(point: Point)
    fun getPoint(): Point?
    fun removePoint()
}