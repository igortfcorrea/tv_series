package com.igor.tv_series.presentation.helpers

import android.text.Editable
import android.text.TextWatcher
import java.util.*

class EditTextWatcher(private val callback: (term: String) -> Unit) : TextWatcher {
    private var timer: Timer? = null

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        timer?.cancel()
    }

    override fun afterTextChanged(s: Editable?) {
        if (s != null) {
            timer = Timer()

            timer?.run {
                this.schedule(
                    object : TimerTask() {
                        override fun run() {
                            callback(s.toString())
                        }
                    },
                    SEARCH_TIME_MILLISECONDS
                )
            }
        }
    }

    companion object {
        private const val SEARCH_TIME_MILLISECONDS = 200L
    }
}