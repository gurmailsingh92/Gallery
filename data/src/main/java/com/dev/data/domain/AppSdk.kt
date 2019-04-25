package com.dev.data.domain

import android.content.Context
import androidx.room.Room

class AppSdk {

    companion object : NoArgSingletonHolder<AppSdk>(::AppSdk)

    private val dbName = "image_db"
    private lateinit var imageDatabase: ImageDatabase
    private lateinit var context: Context

    fun init(context: Context) {
        this.context = context
        imageDatabase = Room.databaseBuilder(context, ImageDatabase::class.java, dbName).build()
    }

    fun getDatabase(): ImageDatabase {
        return imageDatabase
    }
}