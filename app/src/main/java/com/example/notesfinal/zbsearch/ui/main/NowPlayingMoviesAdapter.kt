package com.example.notesfinal.zbsearch.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.model.Movie

class NowPlayingMoviesAdapter(val onClick: (Movie) -> Unit) :
    RecyclerView.Adapter<NowPlayingMoviesAdapter.MainHolder>() {

    var listOfMovies = emptyList<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_now_playing_item, parent, false)
        return MainHolder(view)
    }

    override fun onBindViewHolder(holder: MainHolder, position: Int) {
        with(holder) {
            poster.setImageResource(R.mipmap.ic_launcher)
            nameOfMovie.text = listOfMovies[position].name
            yearOfMovie.text = listOfMovies[position].year
            ratingOfMovie.text = listOfMovies[position].rating
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