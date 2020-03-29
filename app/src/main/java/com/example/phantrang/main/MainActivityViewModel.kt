package com.example.phantrang.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.phantrang.data.db.model.NumberItem
import com.example.phantrang.data.repo.NumberRepository

class MainActivityViewModel(private val repository: NumberRepository): ViewModel() {

    var numberList: LiveData<PagedList<NumberItem>> = repository.getNumberListFromRoom()

    // livedata này phụ thuộc vào size của livedata numberlist
    var isLoading = Transformations.map(numberList) {
        if (it.size == 0) {
            true
        } else false

    }

    fun refresh() {
        repository.refresh()
    }
}