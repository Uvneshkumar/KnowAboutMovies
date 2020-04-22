package com.bigbrains.knowaboutmovies.webservices

/**
 * Holds the response and the status
 */
open class Response<ResponseType>(
    var data: ResponseType?,
    var message: String?,
    var status: Status,
    var code: Int? = null
)

/**
 * Status of the data fetching process
 */
enum class Status {
    SUCCESS, FAILURE, LOADING
}