package com.dev.data.domain

import android.util.Log
import com.dev.data.domain.daorepository.ImageRepository
import com.dev.data.domain.entity.ImageEntity
import com.dev.data.domain.entity.ImageModelEntity
import com.dev.data.domain.repository.Repository

object Data {

    suspend fun getImages(
        searchKey: String,
        itemsToFetch: Int,
        pageNo: Int
    ): Triple<Boolean, List<ImageModelEntity>?, Error?> {


        val images = ImageRepository.getImages(
            searchKey,
            itemsToFetch,
            pageNo * itemsToFetch
        )
        if (images?.isNotEmpty() == true) {
            return Triple(
                true,
                images,
                null
            )
        } else {
            val triple: Triple<Boolean, ImageEntity?, Error?> =
                Repository.GetImages(searchKey, itemsToFetch, pageNo).getData()
            return if (triple.second?.photosContainerEntity?.photos?.isNotEmpty() == true) {
                val imageList = arrayListOf<ImageModelEntity>()
                triple.second?.photosContainerEntity?.photos?.forEach { photoEntity ->
                    photoEntity.transformToImageModel(searchKey)?.let { imageModelEntity ->
                        imageList.add(imageModelEntity)
                    }
                }
                ImageRepository.insertImages(imageList)

                val imagesFromLocal = ImageRepository.getImages(
                    searchKey,
                    itemsToFetch,
                    pageNo * itemsToFetch
                )
                if (imagesFromLocal?.isNotEmpty() == true) {
                    Triple(
                        true,
                        imagesFromLocal,
                        null
                    )
                } else {
                    Triple(true, imageList, null)
                }
            } else {
                if (triple.first) {
                    Triple(false, null, Error("No result found for the given query!!"))
                } else {
                    Triple(false, null, triple.third)
                }
            }
        }

    }


//    suspend private fun getFromDB() {
//        val images = ImageRepository.getImages("car", 3, 3)
//        Log.d("HELLO:", images.toString())
//    }
//
//    suspend private fun saveToDb() {
//        val list = arrayListOf<ImageModelEntity>(
//            ImageModelEntity(
//                id = "1",
//                imageUrl = "car1",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "2",
//                imageUrl = "car2",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "3",
//
//                imageUrl = "car3",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "4",
//
//                imageUrl = "car4",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "5",
//
//                imageUrl = "car5",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "1",
//
//                imageUrl = "bike1",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "7",
//
//                imageUrl = "bike2",
//                key = "bike"
//            ),
//            ImageModelEntity(
//                id = "8",
//
//                imageUrl = "bike3",
//                key = "bike"
//            ),
//            ImageModelEntity(
//                id = "9",
//
//                imageUrl = "bike4",
//                key = "bike"
//            ),
//            ImageModelEntity(
//                id = "10",
//
//                imageUrl = "car6",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "11",
//
//                imageUrl = "car7",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "12",
//
//                imageUrl = "car8",
//                key = "car"
//            ),
//            ImageModelEntity(
//                id = "13",
//
//                imageUrl = "bike5",
//                key = "bike"
//            ),
//            ImageModelEntity(
//                id = "14",
//
//                imageUrl = "bike6",
//                key = "bike"
//            ),
//            ImageModelEntity(
//                id = "15",
//
//                imageUrl = "bike7",
//                key = "bike"
//            )
//        )
//        ImageRepository.insertImages(list)
//    }


}