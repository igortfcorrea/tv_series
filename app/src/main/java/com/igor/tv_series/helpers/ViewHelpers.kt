package com.igor.tv_series.helpers

import android.content.Context
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

fun View.fadeIn(millis: Long = 400) {
    this.animate().alpha(1f).setDuration(millis).start()
}

fun View.fadeOut(millis: Long = 400) {
    this.animate().alpha(0f).setDuration(millis).start()
}

fun ImageView.loadImage(context: Context, url: String) {
    Glide.with(context)
        .load(url)
        .into(this)
}
