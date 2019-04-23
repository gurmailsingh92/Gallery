package com.dev.entensions

import android.content.res.Resources


fun <T : Collection<*>> T?.letEmpty(f: (it: T) -> Unit) {
    if (this != null && this.isNotEmpty()) f(this)
}

fun String?.letEmpty(f: (it: String) -> Unit) {
    if (this != null && this.isNotEmpty()) f(this)
}

fun Resources.getScaledPixel(dimenRes: Int): Int{
    return (getDimensionPixelSize(dimenRes) * configuration.smallestScreenWidthDp/360f).toInt()
}