package com.jyotirmayg.mvvmsample.data.db.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = false)
    val id: Int? = null,
    val quote: String? = null,
    val author: String? = null,
    val thumbnail: String? = null,
    val created_at: String? = null,
    val updated_at: String? = null
)