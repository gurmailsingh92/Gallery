package com.dev.widgets

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

open class MyImageView(context: Context, attributeSet: AttributeSet?) : AppCompatImageView(context, attributeSet) {

    fun setImageUri(url: String) {
        Glide.with(this).load(url).into(this)
    }
}