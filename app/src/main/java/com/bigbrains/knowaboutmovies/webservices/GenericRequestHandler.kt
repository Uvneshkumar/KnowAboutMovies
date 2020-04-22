package com.bigbrains.knowaboutmovies.webservices

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import retrofit2.Call

/**
 *  Generic request handler which handles request and response data
 */
abstract class GenericRequestHandler<R>
@MainThread constructor() {
    private val liveData = MutableLiveData<ResponseWrapper<R>>()
    private val errorHandler: ApiErrorHandler = ApiErrorHandler()

    init {
        doRequest()
    }

    /**
     * Perform API request
     *
     * @return Retrofit call
     */
    protected abstract fun makeRequest(): Call<R>

    /**
     * Override to give custom implementation of Retrofit enque function
     */
    open fun doRequestInternal(liveData: MutableLiveData<ResponseWrapper<R>>) {
        makeRequest().enqueue(object : APICallback<R>(errorHandler) {
            override fun handleResponseData(data: R?, statusCode: StatusCode) {
                liveData.value = ResponseWrapper(data = data, statusCode = statusCode)
            }

            override fun handleException(ex: Exception, statusCode: StatusCode) {
                liveData.value = ResponseWrapper(ex, statusCode = statusCode)
            }
        })
    }

    /**
     *  Request and get live data
     */
    private fun doRequest(): LiveData<ResponseWrapper<R>> {
        executeRequest(liveData)
        return liveData
    }

    /**
     *  Pass the request to retrofit call
     */
    open fun executeRequest(liveData: MutableLiveData<ResponseWrapper<R>>) {
        doRequestInternal(liveData)
    }

    /**
     *  Convert live data to response type
     */
    fun asLiveData() = liveData as LiveData<ResponseWrapper<R>>
}