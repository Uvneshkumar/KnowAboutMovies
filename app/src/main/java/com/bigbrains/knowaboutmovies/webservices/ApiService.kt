package com.bigbrains.knowaboutmovies.webservices

import androidx.lifecycle.MutableLiveData
import retrofit2.Call

class ApiService {
    companion object {
        fun <ResponseType> makeApiRequest(
            requestCall: Call<ResponseType>,
            successHandler: OnSuccess<ResponseType>? = null
        ): MutableLiveData<Response<ResponseType>> {
            val responseLiveData = MutableLiveData<Response<ResponseType>>()
            responseLiveData.value = Response(null, "", Status.LOADING)
            val genResponseLiveData = object : GenericRequestHandler<ResponseType>() {
                override fun makeRequest() =
                    if (requestCall.isExecuted) requestCall.clone() else requestCall
            }.asLiveData()
            genResponseLiveData.observeForever {
                val response = it.data
                if (response != null) {
                    responseLiveData.postValue(
                        if (successHandler == null) {
                            Response(
                                response,
                                "",
                                Status.SUCCESS,
                                200
                            )
                        } else {
                            successHandler.onSuccess(it)
                        }
                    )
                }
            }
            return responseLiveData
        }
    }
}