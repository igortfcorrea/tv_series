package com.igor.tv_series.helpers

import android.view.View

fun View.fadeIn(millis: Long = 400) {
    this.animate().alpha(1f).setDuration(millis).start()
}

fun View.fadeOut(millis: Long = 400) {
    this.animate().alpha(0f).setDuration(millis).start()
}
