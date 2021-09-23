package com.sol.soccerleague.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T):
        ApiResultWrapper<T> {
    return withContext(dispatcher) {
        try {
            ApiResultWrapper.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is IOException -> ApiResultWrapper.NetworkError
                is HttpException -> {
                    ApiResultWrapper.GenericError
                }
                else -> {
                    ApiResultWrapper.GenericError
                }
            }
        }
    }
}

fun isInternetAvailable(context: Context): Boolean {
    var isConnected = false
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
    if (activeNetwork != null && activeNetwork.isConnected)
        isConnected = true
    return isConnected
}