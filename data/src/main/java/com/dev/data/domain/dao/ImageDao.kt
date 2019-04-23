package com.dev.data.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.dev.data.domain.entity.ImageEntity

@Dao
abstract class ImageDao {

    @Insert
    abstract fun insert(list: List<ImageEntity>)

    @Query("SELECT * from ImageEntity")
    abstract fun getImages(): List<ImageEntity>?

}