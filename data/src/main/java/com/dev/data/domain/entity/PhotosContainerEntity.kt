package com.dev.data.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotosContainerEntity(
    @SerializedName("page") @Expose val page: String? = null,
    @SerializedName("pages") @Expose val pages: String? = null,
    @SerializedName("perpage") @Expose val perPage: String? = null,
    @SerializedName("total") @Expose val total: String? = null,
    @SerializedName("photo") @Expose val photos: List<PhotoEntity>? = null
)