package com.igor.tv_series.helpers

fun Float.toPercent(): String {
    return String.format("%.2f",this*100f)+"%";
}