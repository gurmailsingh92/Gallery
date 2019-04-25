package com.media.gallery.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dev.data.domain.Data
import com.dev.data.domain.entity.ImageEntity
import com.dev.data.domain.entity.ImageModelEntity
import com.dev.viewmodel.BaseViewModel

class ImageViewModel : BaseViewModel() {

    private val imagesListLD: MutableLiveData<Triple<Boolean, List<ImageModelEntity>?, Error?>> by lazy {
        MutableLiveData<Triple<Boolean, List<ImageModelEntity>?, Error?>>()
    }

    fun getImagesList(
        searchKey: String,
        itemsToFetch: Int,
        pageNo: Int
    ): LiveData<Triple<Boolean, List<ImageModelEntity>?, Error?>> {
        launchBackGround {
            imagesListLD.postValue(Data.getImages(searchKey, itemsToFetch, pageNo))
        }
        return imagesListLD
    }
}