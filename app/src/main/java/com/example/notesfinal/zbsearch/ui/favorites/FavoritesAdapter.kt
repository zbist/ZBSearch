package com.example.notesfinal.zbsearch.ui.favorites

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

class FavoritesAdapter(val onClick: (Movie) -> Unit) : RecyclerView.Adapter<FavoritesAdapter.Holder>() {

    var listOfMovies = emptyList<Movie>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): Holder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_upcoming_item, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        with(holder) {
            Glide.with(poster)
                .load("${BuildConfig.IMG_URL}${listOfMovies[position].posterPath}")
                .into(poster)

            nameOfMovie.text = listOfMovies[position].title
            dateOfStartMovie.text = listOfMovies[position].releaseDate
        }
    }

    override fun getItemCount() = listOfMovies.size

    inner class Holder(view: View) : RecyclerView.ViewHolder(view) {
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