package com.bigbrains.knowaboutmovies.webservices

import okhttp3.ResponseBody

/**
 * Generic Exception class with data and error body
 */
open class GenericException(
    val errorBody: ResponseBody?,
    message: String?,
    cause: Throwable?,
    val statusCode: StatusCode
) : Exception(message, cause)