package com.dev.data.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(primaryKeys = ["id", "key"])
data class ImageModelEntity(
    val id: String,
    val imageUrl: String,
    val key: String
)
//{
//    @PrimaryKey(autoGenerate = true)
//    private val id: Int = 0
//}