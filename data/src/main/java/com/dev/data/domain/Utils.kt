package com.dev.data.domain

import android.net.Uri
import android.util.Log
import com.dev.data.domain.NetworkConstants.FORMAT
import com.dev.data.domain.NetworkConstants.JSON_CALL_BACK
import com.dev.data.domain.NetworkConstants.KEY_PARAM
import com.dev.data.domain.NetworkConstants.PAGE
import com.dev.data.domain.NetworkConstants.PAGES
import com.dev.data.domain.NetworkConstants.SEARCH
import com.dev.data.domain.entity.ImageEntity
import com.dev.data.domain.entity.PhotoEntity

object Utils {

    fun getFlickrApiUrl(searchKey: String, itemsToFetch: Int, pageNo: Int): String {
        try {

            val builtUri = Uri.parse(NetworkConstants.FLICKR_API_BASE_URL).buildUpon()
                .appendQueryParameter(KEY_PARAM, Constants.FLICKR_API_KEY)
                .appendQueryParameter(SEARCH, searchKey)
                .appendQueryParameter(PAGES, itemsToFetch.toString())
                .appendQueryParameter(PAGE, pageNo.toString())
                .appendQueryParameter(FORMAT, "json")
                .appendQueryParameter(JSON_CALL_BACK, "1")
                .build()

            Log.e("URL", builtUri.toString())

            return builtUri.toString()

        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""

    }

    fun getImageUrl(image: PhotoEntity): String {
        try {
            return "https://farm" + image.farm + ".staticflickr.com/" + image.server +
                    "/" + image.id + "_" + image.secret + ".jpg"
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}