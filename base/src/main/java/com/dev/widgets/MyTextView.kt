package com.dev.widgets

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.dev.R

class MyTextView(context: Context, attributeSet: AttributeSet?) : AppCompatTextView(context, attributeSet) {

    private var backColorPalette: ArrayList<Int> = ArrayList()
    private var showGradient: Boolean = false
    private var cornerRadius: Int? = null
    private var strokeColor: Int? = null
    private var strokeWidth: Int = 2
    var backgroundShape: GradientDrawable? = null

    init {
        val styled = context.theme.obtainStyledAttributes(attributeSet, R.styleable.MyTextView, 0, 0)

        showGradient = styled.getBoolean(
            R.styleable.MyTextView_showGradient,
            false
        )

        if (styled.hasValue(R.styleable.MyTextView_cornerRadius)) {
            cornerRadius = styled.getDimensionPixelSize(
                R.styleable.MyTextView_cornerRadius,
                resources.getDimensionPixelSize(R.dimen.corner_radius)
            )
        }

        if (styled.hasValue(R.styleable.MyTextView_backColor)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyTextView_backColor,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }
        if (styled.hasValue(R.styleable.MyTextView_backColorCenter)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyTextView_backColorCenter,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }
        if (styled.hasValue(R.styleable.MyTextView_backColorEnd)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyTextView_backColorEnd,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }

        if (styled.hasValue(R.styleable.MyTextView_strokeColor)) {
            strokeColor = styled.getColor(
                R.styleable.MyTextView_strokeColor,
                ContextCompat.getColor(context, R.color.transparent)
            )
        }

        strokeWidth = styled.getDimensionPixelSize(
            R.styleable.MyTextView_strokeWidth,
            resources.getDimensionPixelSize(R.dimen.stroke_width_regular)
        )

        if (showGradient) {
            getShapeDrawable().colors = backColorPalette.toIntArray()
        }
        if (background != null && background is ColorDrawable) {
            getShapeDrawable().setColor((background as ColorDrawable).color)
        }
        cornerRadius?.let {
            getShapeDrawable().cornerRadius = it.toFloat()
        }
        strokeColor?.let {
            getShapeDrawable().setStroke(strokeWidth, it)
        }
        backgroundShape?.let {
            background = it
        }
    }

    private fun getShapeDrawable(): GradientDrawable {
        if (backgroundShape == null) {
            backgroundShape = GradientDrawable()
        }
        return backgroundShape!!
    }

    override fun setBackground(background: Drawable?) {
        if (background != null && background is ColorDrawable) {
            getShapeDrawable().setColor(background.color)
        }
        super.setBackground(getShapeDrawable())
    }

    fun setTextIfAny(text: String?) {
        if (text != null) {
            setText(text)
        }
    }

}