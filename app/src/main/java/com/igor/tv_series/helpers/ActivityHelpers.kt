package com.igor.tv_series.helpers

import android.app.Activity
import android.view.View
import android.view.inputmethod.InputMethodManager

fun Activity.hideSoftKeyboard(view: View) {
    val inputManagerMethod = this.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputManagerMethod.hideSoftInputFromWindow(view.windowToken, 0)
}
