package com.sol.soccerleague.api

sealed class ApiResultWrapper<out T> {
    data class Success<out T>(val value: T): ApiResultWrapper<T>()
    object GenericError: ApiResultWrapper<Nothing>()
    object NetworkError: ApiResultWrapper<Nothing>()
}