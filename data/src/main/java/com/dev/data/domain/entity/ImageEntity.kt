package com.dev.data.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ImageEntity(
    @SerializedName("photos") @Expose val photosContainerEntity: PhotosContainerEntity? = null,
    @SerializedName("stat") @Expose val stat: String? = null
)