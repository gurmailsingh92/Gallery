package com.dev.data.domain

import com.dev.data.domain.daorepository.ImageRepository
import com.dev.data.domain.entity.ImageEntity
import com.dev.data.domain.repository.Repository

object Data {

    suspend fun getImages(): Triple<Boolean, List<ImageEntity>?, Error?> {
        val images = ImageRepository.getImages()
        if (images?.isNotEmpty() == true) {
            return Triple(true, images, null)
        }
        val triple: Triple<Boolean, List<ImageEntity>?, Error?> = Repository.GetImages().getData()
        if (triple.first) {
            triple.second?.let {
                ImageRepository.insertImages(it)
            }
        }
        return triple
    }

}