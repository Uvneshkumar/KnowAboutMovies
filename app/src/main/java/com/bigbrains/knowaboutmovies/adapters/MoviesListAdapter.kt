package com.bigbrains.knowaboutmovies.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bigbrains.knowaboutmovies.R
import com.bigbrains.knowaboutmovies.activities.MovieDetailActivity
import com.bigbrains.knowaboutmovies.models.responses.Results
import com.bigbrains.knowaboutmovies.utils.MOVIE_ID
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.view_movie.view.*
import java.util.*

class MoviesListAdapter(
    private val context: Context,
    private val items: List<Results>
) :
    RecyclerView.Adapter<MoviesListAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_movie, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            context.startActivity(Intent(context, MovieDetailActivity::class.java).apply {
                putExtra(MOVIE_ID, items[position].id)
            })
        }
        Glide.with(context).load("https://image.tmdb.org/t/p/w342" + items[position].poster_path)
            .into(holder.itemView.movie_poster)
        holder.itemView.txtName.text = items[position].title
        holder.itemView.txtDescription.text = items[position].overview
        holder.itemView.txtRating.text = items[position].vote_average.toString()
        holder.itemView.txtDate.text = items[position].release_date
        holder.itemView.txtLanguage.text =
            items[position].original_language.toUpperCase(Locale.ROOT)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}
}