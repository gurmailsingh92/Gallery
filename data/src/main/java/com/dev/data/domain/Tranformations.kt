package com.dev.data.domain

import com.dev.data.domain.entity.ImageModelEntity
import com.dev.data.domain.entity.PhotoEntity

fun PhotoEntity.transformToImageModel(key: String): ImageModelEntity? {
    return if (id?.isNotEmpty() == true) {
        ImageModelEntity(
            id = id,
            key = key,
            imageUrl = Utils.getImageUrl(this)
        )

    } else {
        null
    }
}