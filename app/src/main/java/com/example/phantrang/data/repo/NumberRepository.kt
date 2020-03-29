package com.example.phantrang.data.repo

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.example.phantrang.data.api.Firestore
import com.example.phantrang.data.db.DataCallBack
import com.example.phantrang.data.db.NumberDao
import com.example.phantrang.data.db.model.NumberItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NumberRepository(private val firestore: Firestore, private val numberDao: NumberDao) {


    fun getNumberListFromRoom(): LiveData<PagedList<NumberItem>> {
        val factory = numberDao.getAllNumber()
        val config = PagedList.Config.Builder()
            .setPageSize(20) // set 1 page 20 item
            .setPrefetchDistance(10) // set load trước khi tới  10 item cuối của mỗi page
            .setInitialLoadSizeHint(15)// page số 1 15 item
            .build()
        // new ra su kien khi table trong Room het data, or chua co data
        val boundaryCallback = DataCallBack {
            downloadOneNumbersPageIntoDB()
        }
        val liveData = LivePagedListBuilder(factory, config)
            .setBoundaryCallback(boundaryCallback).build()
        return liveData

    }

    fun refresh() {
       GlobalScope.launch {
           firestore.refresh()
           numberDao.deleteAllNumber() // xoa toàn bộ item trong table để tự kích hoạt sự kiện OnzeroItemLoaded
       }
    }

   private fun downloadOneNumbersPageIntoDB() {

        firestore.getOnePage {
            GlobalScope.launch {
                withContext(Dispatchers.IO) {
                    numberDao.insertList(it) // add list phan trang tu firestore ve room
                }

            }
        }
    }
}