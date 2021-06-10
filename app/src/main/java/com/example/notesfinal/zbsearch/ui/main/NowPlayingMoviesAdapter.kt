package com.example.notesfinal.zbsearch.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.notesfinal.zbsearch.BuildConfig
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.model.Movie

class NowPlayingMoviesAdapter(val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<NowPlayingMoviesAdapter.MainHolder>() {

    var listOfMovies = emptyList<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_now_playing_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        with(holder) {
            Glide.with(poster)
                .load("${BuildConfig.IMG_URL}${listOfMovies[position].posterPath}")
                .into(poster)

            nameOfMovie.text = listOfMovies[position].title
            yearOfMovie.text = listOfMovies[position].releaseDate.subSequence(0, 4)
            ratingOfMovie.text = listOfMovies[position].voteAverage
        }
    }

    override fun getItemCount() = listOfMovies.size

    inner class MainHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster = view.findViewById<ImageView>(R.id.poster_of_movie)
        val nameOfMovie = view.findViewById<TextView>(R.id.name_of_movie)
        val yearOfMovie = view.findViewById<TextView>(R.id.year_of_movie)
        val ratingOfMovie = view.findViewById<TextView>(R.id.rating_of_movie)

        init {
            view.setOnClickListener {
                onClick.invoke(listOfMovies[adapterPosition])
            }
        }
    }
}