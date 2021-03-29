package com.enestekin.simplecachingexample.util

sealed class Resource<T>(
    val data: T? = null,
    val throwable: Throwable? = null
) {
    class Success<T> (data: T?) : Resource<T>(data)
    class Loading<T> (data: T? = null)  : Resource<T>(data)  //default value is null
    class Error<T>(throwable: Throwable,data: T? = null) : Resource<T>(data,throwable)


}