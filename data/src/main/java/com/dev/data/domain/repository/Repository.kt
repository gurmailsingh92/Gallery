package com.dev.data.domain.repository

import com.dev.data.domain.ApiHelper
import com.dev.data.domain.Utils
import com.dev.data.domain.entity.ImageEntity

sealed class Repository<T>(val path: String, val bodyParams: Map<String, Any>? = null) {

    class GetImages(searchKey: String, itemsToFetch: Int, pageNo: Int) : Repository<ImageEntity>(
        Utils.getFlickrApiUrl(
            searchKey,
            itemsToFetch,
            pageNo
        )
    )

    suspend inline fun <reified T : Any> getData(): Triple<Boolean, T?, Error?> {
        return ApiHelper.getData(path, listOf())
    }

    suspend inline fun <reified T : Any> getPostData(): Triple<Boolean, T?, Error?> {
        return ApiHelper.getPostData(path, listOf(), bodyParams!!)
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
