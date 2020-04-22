package com.bigbrains.knowaboutmovies.webservices

import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

/**
 * ApiErrorHandler class should be extended to handle custom exceptions
 **/
open class ApiErrorHandler {
    /**
     *  Method should be overridden to return custom exception type which
     *  would be a sub-type of Exception or to have the response body,
     *  return a sub-type of kexception()
     */
    open fun getExceptionType(response: retrofit2.Response<*>): Exception {
        var message = ""
        try {
            val jObjError = JSONObject(response.errorBody()?.string().toString())
            message = jObjError.optString("message")
        } catch (e1: JSONException) {
            e1.printStackTrace()
        } catch (e1: IOException) {
            e1.printStackTrace()
        }
        return GenericException(
            response.errorBody(),
            message,
            null,
            StatusCode(response.code())
        )
    }

}