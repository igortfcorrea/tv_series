package com.igor.tv_series.data.infra

suspend inline fun <T> safeRoomCall(
    crossinline apiCall: suspend () -> T
): Result<T> {
    return try {
        val response = apiCall()
        Result.success(response)
    } catch (e: Exception) {
        Result.failure(e)
    }
}