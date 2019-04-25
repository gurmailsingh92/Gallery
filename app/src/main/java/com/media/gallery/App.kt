package com.media.gallery

import android.app.Application
import com.dev.data.domain.AppSdk

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        AppSdk.getInstance().init(this)
    }
}