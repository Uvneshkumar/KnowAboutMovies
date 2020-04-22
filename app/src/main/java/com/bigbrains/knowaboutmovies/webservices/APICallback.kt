package com.bigbrains.knowaboutmovies.webservices

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.HttpURLConnection

/**
 * Generic callback class for handling web service response
 */
abstract class APICallback<T>(private val errorHandler: ApiErrorHandler) : Callback<T> {

    override fun onResponse(call: Call<T>, response: Response<T>) {
        val statusCode = StatusCode(response.code())
        when (statusCode.code) {
            HttpURLConnection.HTTP_OK -> handleResponseData(response.body(), statusCode)

            else -> handleError(response, statusCode)
        }
    }

    protected abstract fun handleResponseData(data: T?, statusCode: StatusCode)

    private fun handleError(response: Response<T>, statusCode: StatusCode) {
        handleException(errorHandler.getExceptionType(response), statusCode)
    }

    protected abstract fun handleException(ex: Exception, statusCode: StatusCode)

    override fun onFailure(call: Call<T>, t: Throwable) {
        if (t is Exception) {
            handleException(t, StatusCode(-1))
        } else {
            //do something else
        }
    }

}