package com.bigbrains.knowaboutmovies.webservices

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitBuilder {
    private const val TIME_OUT = 30
    fun build(): Retrofit {
        val httpClient = OkHttpClient.Builder()
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        httpClient.addInterceptor(logging)
        httpClient.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val client = httpClient
            .connectTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIME_OUT.toLong(), TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create()
        return Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(client)
            .build()
    }
}
