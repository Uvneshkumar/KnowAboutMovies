package com.bigbrains.knowaboutmovies.webservices

import com.google.gson.Gson
import com.google.gson.JsonElement

class ResponseParser<T> {

    fun parseResponseAndGetData(
        response: JsonElement,
        classType: Class<T>
    ): T {
        return Gson().fromJson(response, classType)
    }

}