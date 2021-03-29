package com.enestekin.simplecachingexample.util

import kotlinx.coroutines.flow.*

inline fun <ResultType,RequestType>networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true}
) = flow {
val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))

        try { // internet connection can be fail
            saveFetchResult(fetch())
            query().map { Resource.Success(it) } // it is what we get from query fun


        }catch (throwable: Throwable) {
            query().map { Resource.Error(throwable,it) }
        }
    }else {
        query().map { Resource.Success(it) }
    }
    emitAll(flow)  // we delecrated if block  to flow  variable  to get all query() result
}