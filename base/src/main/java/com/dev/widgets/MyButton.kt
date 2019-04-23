package com.dev.widgets

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.dev.R

class MyButton(context: Context, attributeSet: AttributeSet?) : AppCompatButton(context, attributeSet) {

    init {
        val styled = context.theme.obtainStyledAttributes(attributeSet, R.styleable.MyButton, 0, 0)
        val cornerRadius = styled.getDimensionPixelSize(
            R.styleable.MyButton_cornerRadius,
            resources.getDimensionPixelSize(R.dimen.corner_radius)
        )
        val shape = GradientDrawable()
        shape.cornerRadius = cornerRadius.toFloat()
        if (background != null && background is ColorDrawable) {
            shape.setColor((background as ColorDrawable).color)
        }
        background = shape
    }

}