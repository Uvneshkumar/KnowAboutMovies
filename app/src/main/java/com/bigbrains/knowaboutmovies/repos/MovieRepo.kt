package com.bigbrains.knowaboutmovies.repos

import androidx.lifecycle.MutableLiveData
import com.bigbrains.knowaboutmovies.models.responses.Movie
import com.bigbrains.knowaboutmovies.models.responses.Movies
import com.bigbrains.knowaboutmovies.webservices.ApiService
import com.bigbrains.knowaboutmovies.webservices.Response
import com.bigbrains.knowaboutmovies.webservices.RetrofitBuilder

object MovieRepo {

    private val movieRequests: MovieRequests by lazy {
        RetrofitBuilder.build().create(MovieRequests::class.java)
    }

    fun getMovies(): MutableLiveData<Response<Movies>> {
        return ApiService.makeApiRequest(
            movieRequests.getMovies()
        )
    }

    fun getMovieDetail(movieId: Float): MutableLiveData<Response<Movie>> {
        return ApiService.makeApiRequest(
            movieRequests.getMovieDetail(movieId)
        )
    }

}