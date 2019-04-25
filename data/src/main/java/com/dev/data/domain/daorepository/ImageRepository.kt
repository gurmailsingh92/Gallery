package com.dev.data.domain.daorepository

import androidx.annotation.WorkerThread
import com.dev.data.domain.AppSdk
import com.dev.data.domain.entity.ImageModelEntity


class ImageRepository {

    companion object {

        @WorkerThread
        suspend fun insertImages(list: List<ImageModelEntity>) {
            AppSdk.getInstance().getDatabase().imageDao().insert(list)
        }

        @WorkerThread
        suspend fun getImages(
            searchKey: String,
            itemsToFetch: Int,
            offset: Int
        ): List<ImageModelEntity>? {
            return AppSdk.getInstance().getDatabase().imageDao().getImages(
                searchKey,
                itemsToFetch,
                offset
            )
        }


    }
}