package com.conscensia.draganddrop.viewmodels

import android.graphics.Point
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.conscensia.draganddrop.data.repositories.IPointRepository

class DragAndDropViewModel(private val pointRepo: IPointRepository): ViewModel() {

    private val _pointLiveData = MutableLiveData<Point>()
    val pointLiveData: LiveData<Point> = _pointLiveData

    init {
        _pointLiveData.value = pointRepo.getPoint()
    }

    fun onDropped(point: Point) {
        pointRepo.savePoint(point)
        _pointLiveData.value = point
    }
}