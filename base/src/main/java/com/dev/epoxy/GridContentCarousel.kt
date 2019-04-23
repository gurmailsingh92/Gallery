package com.dev.epoxy

import android.content.Context
import android.util.AttributeSet
import androidx.recyclerview.widget.GridLayoutManager
import com.airbnb.epoxy.ModelView
import com.dev.epoxy.ContentCarousel

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class GridContentCarousel @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ContentCarousel(context, attrs, defStyleAttr) {

    override fun createLayoutManager(): LayoutManager {
        return GridLayoutManager(context, 3)
    }
}
