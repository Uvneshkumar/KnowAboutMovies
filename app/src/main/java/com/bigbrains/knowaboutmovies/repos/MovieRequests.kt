package com.bigbrains.knowaboutmovies.repos

import com.bigbrains.knowaboutmovies.models.responses.Movie
import com.bigbrains.knowaboutmovies.models.responses.Movies
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieRequests {
    @GET("/3/discover/movie?api_key=9a2bdc61f7e9de98ec887d51db0cde00&sort_by=popularity.desc")
    fun getMovies(): Call<Movies>

    @GET("/3/movie/{id}?api_key=9a2bdc61f7e9de98ec887d51db0cde00")
    fun getMovieDetail(@Path("id") movieId: Float): Call<Movie>
}