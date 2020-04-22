package com.bigbrains.knowaboutmovies.webservices;

public interface OnSuccess<ResponseType> {
    Response<ResponseType> onSuccess(ResponseWrapper<ResponseType> data);
}
