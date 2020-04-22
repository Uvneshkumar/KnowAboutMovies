package com.bigbrains.knowaboutmovies.webservices;

public interface OnException<ResponseType> {
    Response<ResponseType> onException(ResponseWrapper<ResponseType> data);
}
