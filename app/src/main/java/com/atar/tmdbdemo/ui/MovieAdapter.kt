package com.atar.tmdbdemo.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.atar.tmdbdemo.Constants
import com.atar.tmdbdemo.Movie
import com.atar.tmdbdemo.R
import com.bumptech.glide.Glide

class MovieAdapter(context: Context) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    /**
     * Data
     */
    private var mMovies = emptyList<Movie>()
    private val mInflater: LayoutInflater = LayoutInflater.from(context)

    /**
     * RecyclerView.Adapter Functions
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemView = mInflater.inflate(R.layout.movie, parent, false)
        return MovieViewHolder(itemView)
    }

    override fun getItemCount(): Int = mMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = mMovies[position]
        holder.title.text = movie.title

        val url = "${Constants.TMDB_IMAGE_URL}${movie.posterUrl}"
        Glide.with(holder.itemView)
            .load(url)
            .centerCrop()
            .into(holder.cover)
    }

    /**
     * MovieAdapter Functions
     */
    fun updateMovies(movies: List<Movie>) {
        mMovies = movies
        notifyDataSetChanged()
    }

    /**
     * Internal Classes
     */
    class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.movie_title)
        val cover: ImageView = itemView.findViewById(R.id.movie_cover)
    }

}