package com.bigbrains.knowaboutmovies.activities

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bigbrains.knowaboutmovies.R
import com.bigbrains.knowaboutmovies.adapters.MoviesListAdapter
import com.bigbrains.knowaboutmovies.common.views.BaseActivity
import com.bigbrains.knowaboutmovies.viewmodels.MoviesListViewModel
import com.bigbrains.knowaboutmovies.webservices.Status
import kotlinx.android.synthetic.main.activity_movies_list.*

class MoviesListActivity : BaseActivity() {

    private lateinit var moviesVM: MoviesListViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movies_list)
        title = "Know about Movies"
        moviesVM = ViewModelProviders.of(this).get(MoviesListViewModel::class.java)
        loadData()
    }

    private fun loadData() {
        moviesVM.getMoviesLiveData().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    listOfMovies.adapter =
                        MoviesListAdapter(this, it.data?.results ?: mutableListOf())
                    hideProgress()
                }
                Status.FAILURE -> {
                    hideProgress()
                    showErrorDialog(it.message.toString())
                }
                Status.LOADING -> {
                    showProgress("Fetching Movies...")
                }
            }
        })
    }
}
