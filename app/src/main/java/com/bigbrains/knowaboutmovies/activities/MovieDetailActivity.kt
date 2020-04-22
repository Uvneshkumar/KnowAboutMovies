package com.bigbrains.knowaboutmovies.activities

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bigbrains.knowaboutmovies.R
import com.bigbrains.knowaboutmovies.common.views.BaseActivity
import com.bigbrains.knowaboutmovies.utils.MOVIE_ID
import com.bigbrains.knowaboutmovies.utils.Utils.getDaySuffix
import com.bigbrains.knowaboutmovies.utils.Utils.getMonth
import com.bigbrains.knowaboutmovies.utils.Utils.truncateNumber
import com.bigbrains.knowaboutmovies.viewmodels.MovieDetailViewModel
import com.bigbrains.knowaboutmovies.webservices.Status
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_movie_detail.*

class MovieDetailActivity : BaseActivity() {

    private lateinit var movieVM: MovieDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        movieVM = ViewModelProviders.of(this).get(MovieDetailViewModel::class.java)
        loadData()
    }

    private fun loadData() {
        movieVM.getMovieDetail(intent.getFloatExtra(MOVIE_ID, 0F)).observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    if (it.data != null) {
                        title = it.data!!.title
                        Glide.with(this)
                            .load("https://image.tmdb.org/t/p/w342" + it.data!!.backdrop_path)
                            .into(imageView)
                        description.text = it.data!!.overview
                        runtime.text = it.data!!.runtime.toString() + " minutes"
                        val releaseDate: MutableList<String> =
                            it.data!!.release_date.split("-").toMutableList()
                        if (releaseDate[2][0] == '0') {
                            releaseDate[2] = releaseDate[2].substring(1)
                        }
                        date.text = releaseDate[2] + getDaySuffix(
                            releaseDate[2].toInt()
                        ).toString() + " " + getMonth(
                            releaseDate[1].toInt()
                        ).toString() + " " + releaseDate[0]
                        rating.text = it.data!!.vote_average.toString()
                        var genres = ""
                        for (gen in it.data!!.genres) {
                            genres = genres + ", " + gen.name
                        }
                        if (genres.isNotEmpty()) {
                            genre.text = genres.substring(2)
                        }
                        language.text = it.data!!.original_language.toUpperCase()
                        budget.text = "$${truncateNumber(it.data!!.budget)}"
                        revenue.text = "$${truncateNumber(it.data!!.revenue)}"
                    }
                    hideProgress()
                }
                Status.FAILURE -> {
                    hideProgress()
                    showErrorDialog(it.message.toString())
                }
                Status.LOADING -> {
                    showProgress("Fetching Movie detail...")
                }
            }
        })
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}
