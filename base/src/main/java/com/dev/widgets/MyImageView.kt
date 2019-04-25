package com.dev.widgets

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.transition.Transition

open class MyImageView(context: Context, attributeSet: AttributeSet?) : AppCompatImageView(context, attributeSet) {

    fun setImageUri(url: String, placeholderImage: ColorDrawable) {
        Glide.with(this).load(url).placeholder(placeholderImage).error(placeholderImage)
            .transition(DrawableTransitionOptions.withCrossFade(200))
            .into(this)
    }
}