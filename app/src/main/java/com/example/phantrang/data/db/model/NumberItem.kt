package com.example.phantrang.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class NumberItem(@PrimaryKey val id: Int = 0,
                      var content: Int = -1)