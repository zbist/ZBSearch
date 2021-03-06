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

class UpcomingMoviesAdapter(val onClick: (Movie) -> Unit) : RecyclerView.Adapter<UpcomingMoviesAdapter.UpcomingHolder>() {

    var listOfMovies = emptyList<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): UpcomingHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_upcoming_item, parent, false)
        return UpcomingHolder(view)
    }

    override fun onBindViewHolder(holder: UpcomingHolder, position: Int) {
        with(holder) {
            Glide.with(poster)
                .load("${BuildConfig.IMG_URL}${listOfMovies[position].posterPath}")
                .into(poster)

            nameOfMovie.text = listOfMovies[position].title
            dateOfStartMovie.text = listOfMovies[position].releaseDate
        }
    }

    override fun getItemCount() = listOfMovies.size

    inner class UpcomingHolder(view: View) : RecyclerView.ViewHolder(view) {
        val poster = view.findViewById<ImageView>(R.id.poster_of_movie)
        val nameOfMovie = view.findViewById<TextView>(R.id.name_of_movie)
        val dateOfStartMovie = view.findViewById<TextView>(R.id.date_of_start_movie)

        init {
            view.setOnClickListener {
                onClick(listOfMovies[adapterPosition])
            }
        }
    }
}