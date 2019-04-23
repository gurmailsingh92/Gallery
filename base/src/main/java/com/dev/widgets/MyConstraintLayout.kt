package com.dev.widgets

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.dev.R

class MyConstraintLayout(context: Context, attributeSet: AttributeSet?) : ConstraintLayout(context, attributeSet) {

    private var backColorPalette: ArrayList<Int> = ArrayList()
    private var showGradient: Boolean = false
    private var cornerRadius: Int? = null
    var backgroundShape: GradientDrawable? = null

    init {
        val styled = context.theme.obtainStyledAttributes(attributeSet, R.styleable.MyConstraintLayout, 0, 0)

        showGradient = styled.getBoolean(
            R.styleable.MyConstraintLayout_showGradient,
            false
        )

        if (styled.hasValue(R.styleable.MyConstraintLayout_cornerRadius)) {
            cornerRadius = styled.getDimensionPixelSize(
                R.styleable.MyConstraintLayout_cornerRadius,
                resources.getDimensionPixelSize(R.dimen.corner_radius)
            )
        }

        if (styled.hasValue(R.styleable.MyConstraintLayout_backColor)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyConstraintLayout_backColor,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }
        if (styled.hasValue(R.styleable.MyConstraintLayout_backColorCenter)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyConstraintLayout_backColorCenter,
                    ContextCompat.getColor(context, R.color.transparent)
                )
            )
        }
        if (styled.hasValue(R.styleable.MyConstraintLayout_backColorEnd)) {
            backColorPalette.add(
                styled.getColor(
                    R.styleable.MyConstraintLayout_backColorEnd,
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