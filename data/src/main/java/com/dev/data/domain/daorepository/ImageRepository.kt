package com.dev.data.domain.daorepository

import androidx.annotation.WorkerThread
import com.dev.data.domain.AppSdk
import com.dev.data.domain.entity.ImageEntity


class ImageRepository {

    companion object {

        @WorkerThread
        suspend fun insertImages(list: List<ImageEntity>) {
            AppSdk.getInstance().imageDatabase.imageDao().insert(list)
        }

        @WorkerThread
        suspend fun getImages(): List<ImageEntity>? {
            return AppSdk.getInstance().imageDatabase.imageDao().getImages()
        }


    }
}