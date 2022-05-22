package com.igor.tv_series.domain

sealed class State<out T : Any>
class Loaded<out T : Any>(val result: T) : State<T>()
class Empty(val message: String? = null) : State<Nothing>()
class Error(val message: String?) : State<Nothing>()