package com.dev.data.domain.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class PhotoEntity(
    @SerializedName("id") @Expose val id: String? = null,
    @SerializedName("owner") @Expose val owner: String? = null,
    @SerializedName("secret") @Expose val secret: String? = null,
    @SerializedName("server") @Expose val server: String? = null,
    @SerializedName("farm") @Expose val farm: String? = null,
    @SerializedName("title") @Expose val title: String? = null,
    @SerializedName("ispublic") @Expose val isPublic: String? = null,
    @SerializedName("isfriend") @Expose val isFriend: String? = null,
    @SerializedName("isfamily") @Expose val isFamily: String? = null
)