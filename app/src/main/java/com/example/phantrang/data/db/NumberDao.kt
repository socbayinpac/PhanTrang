package com.example.phantrang.data.db

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.phantrang.data.db.model.NumberItem

@Dao
interface NumberDao {
    @Query("select * from NumberItem")
    fun getAllNumber(): DataSource.Factory<Int, NumberItem>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertList(numberItem: List<NumberItem>)

    @Query("select * from NumberItem")
    fun getAllItem(): List<NumberItem>

    @Query("delete from NumberItem")
    suspend fun deleteAllNumber()

}