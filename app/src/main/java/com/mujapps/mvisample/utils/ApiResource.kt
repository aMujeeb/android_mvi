package com.mujapps.mvisample.utils

sealed class ApiResource<out T> {
    data class Success<T>(val data: T?) : ApiResource<T>()
    data class Error(val errorMessage: String?) : ApiResource<Nothing>()
}