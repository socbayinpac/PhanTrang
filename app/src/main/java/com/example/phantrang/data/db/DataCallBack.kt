package com.example.phantrang.data.db

import android.util.Log
import androidx.paging.PagedList
import com.example.phantrang.data.db.model.NumberItem

class DataCallBack(val onDownload: () -> Unit): PagedList.BoundaryCallback<NumberItem>() {
    override fun onZeroItemsLoaded() { // khi room rỗng
        super.onZeroItemsLoaded()
        onDownload()
        Log.i("----------","Dang down page dau tien tu firestore")
    }

    override fun onItemAtEndLoaded(itemAtEnd: NumberItem) { // khi table room đã hết data, down thêm từ api
        super.onItemAtEndLoaded(itemAtEnd)
        onDownload()
        Log.i("----------","Dang down them 1 page nua tu firestore")
    }
}