package com.bigbrains.knowaboutmovies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigbrains.knowaboutmovies.models.responses.Movie
import com.bigbrains.knowaboutmovies.repos.MovieRepo
import com.bigbrains.knowaboutmovies.webservices.Response

class MovieDetailViewModel : ViewModel() {

    fun getMovieDetail(movieId: Float): MutableLiveData<Response<Movie>> {
        return MovieRepo.getMovieDetail(movieId)
    }
}