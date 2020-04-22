package com.bigbrains.knowaboutmovies.webservices

class ResponseWrapper<T>(
    val exception: Exception? = null,
    val data: T? = null,
    val statusCode: StatusCode
)

class StatusCode(val code: Int)