package com.dev.data.domain.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dev.data.domain.entity.ImageModelEntity

@Dao
abstract class ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(list: List<ImageModelEntity>)

    @Query("SELECT * from ImageModelEntity where `key` = :searchKey LIMIT :itemsToFetch OFFSET :offset")
    abstract fun getImages(
        searchKey: String,
        itemsToFetch: Int,
        offset: Int
    ): List<ImageModelEntity>?

}