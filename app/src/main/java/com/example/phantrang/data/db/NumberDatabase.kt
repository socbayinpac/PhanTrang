package com.example.phantrang.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.phantrang.data.db.model.NumberItem

@Database(entities = [NumberItem::class],version = 1)
abstract class NumberDatabase: RoomDatabase() {
    abstract fun numberDao(): NumberDao

    companion object {
        @Volatile private var instance: NumberDatabase? = null

        fun getInstance(contenxt: Context): NumberDatabase {
            return instance ?: synchronized(this) {
                return instance
                    ?: buildDatabase(contenxt)
            }
        }

        fun buildDatabase(contenxt: Context): NumberDatabase {
            return Room.databaseBuilder(contenxt,
                NumberDatabase::class.java,"NumberDB").build()
        }
    }
}