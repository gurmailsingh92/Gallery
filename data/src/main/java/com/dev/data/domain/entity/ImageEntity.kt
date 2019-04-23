package com.dev.data.domain.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity
class ImageEntity(
    @PrimaryKey
    @SerializedName("id") @Expose val id: String
)