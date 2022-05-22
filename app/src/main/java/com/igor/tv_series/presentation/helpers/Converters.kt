package com.igor.tv_series.presentation.helpers

fun Float.toPercent(): String {
    return String.format("%.2f",this*100f)+"%";
}