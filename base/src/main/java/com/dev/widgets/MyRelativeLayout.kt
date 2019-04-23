package com.dev.widgets

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import com.dev.R


class MyRelativeLayout(context: Context, attributeSet: AttributeSet?) : RelativeLayout(context, attributeSet) {

    private var backColorPalette: ArrayList<Int> = ArrayList()
    private var showGradient: Boolean = false
    private var cornerRadius: Int? = null
    var backgroundShape: GradientDrawable? = null

    init {
        val styled = context.theme.obtainStyledAttributes(attributeSet, R.styleable.MyRelativeLayout, 0, 0)

        showGradient = styled.getBoolean(
            R.styleable.MyRelativeLayout_showGradient,
            false
        )

        if (styled.hasValue(R.styleable.MyRelativeLayout_cornerRadius)) {
            cornerRadius = styled.getDimensionPixelSize(
                R.styleable.MyRelativeLayout_cornerRadius,
                resources.getDimensionPixelSize(R.dimen.corner_radius)
            )
        }

        if (styled.hasValue(R.styleable.MyRelativeLayout_backColor)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyRelativeLayout_backColor,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }
        if (styled.hasValue(R.styleable.MyRelativeLayout_backColorCenter)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyRelativeLayout_backColorCenter,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }
        if (styled.hasValue(R.styleable.MyRelativeLayout_backColorEnd)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyRelativeLayout_backColorEnd,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }

        if (showGradient) {
            getShapeDrawable().colors = backColorPalette.toIntArray()
        }
        if (background != null && background is ColorDrawable) {
            getShapeDrawable().setColor((background as ColorDrawable).color)
        }
        cornerRadius?.let {
            getShapeDrawable().cornerRadius = it.toFloat()
        }
        backgroundShape?.let {
            background = it
        }
    }

    fun getShapeDrawable(): GradientDrawable {
        if (backgroundShape == null) {
            backgroundShape = GradientDrawable()
        }
        return backgroundShape!!
    }

}