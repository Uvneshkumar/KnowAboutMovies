package com.bigbrains.knowaboutmovies.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bigbrains.knowaboutmovies.models.responses.Movies
import com.bigbrains.knowaboutmovies.repos.MovieRepo
import com.bigbrains.knowaboutmovies.webservices.Response

class MoviesListViewModel : ViewModel() {

    fun getMoviesLiveData(): MutableLiveData<Response<Movies>> {
        return MovieRepo.getMovies()
    }
}