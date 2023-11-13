package com.example.merlinproject.common

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Failure(val msg: Throwable?) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}

