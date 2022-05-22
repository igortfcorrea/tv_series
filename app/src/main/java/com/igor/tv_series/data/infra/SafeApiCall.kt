package com.igor.tv_series.data.infra

import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

suspend inline fun <T> safeApiCall(
    crossinline apiCall: suspend () -> T
): Result<T> {
    return try {
        val response = apiCall()
        Result.success(response)
    } catch (e: HttpException) {
        Result.failure(e)
    } catch (e: ConnectException) {
        Result.failure(e)
    } catch (e: SocketTimeoutException) {
        Result.failure(e)
    } catch (e: UnknownHostException) {
        Result.failure(e)
    }
}