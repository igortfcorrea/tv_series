package com.igor.tv_series.presentation.helpers

fun Float.toPercent(): String {
    return String.format("%.1f",this*10f)+"%";
}