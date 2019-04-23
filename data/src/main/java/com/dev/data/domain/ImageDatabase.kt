package com.dev.data.domain

import androidx.room.RoomDatabase
import androidx.room.Database
import com.dev.data.domain.dao.ImageDao
import com.dev.data.domain.entity.ImageEntity

@Database(entities = [ImageEntity::class], version = 1, exportSchema = false)
abstract class ImageDatabase : RoomDatabase() {

    abstract fun imageDao(): ImageDao
}