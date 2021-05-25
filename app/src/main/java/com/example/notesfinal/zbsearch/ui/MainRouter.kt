package com.example.notesfinal.zbsearch.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.ui.favorites.FavoritesFragment
import com.example.notesfinal.zbsearch.ui.main.MainFragment
import com.example.notesfinal.zbsearch.ui.movie.MovieFragment
import com.example.notesfinal.zbsearch.ui.ratings.RatingsFragment

class MainRouter(private val activity: AppCompatActivity) {

    fun openRatings() {
        activity.supportFragmentManager.apply { popBackStack() }.beginTransaction()
            .replace(R.id.container_for_fragments, RatingsFragment.newInstance())
            .commit()
    }

    fun openFavorites() {
        activity.supportFragmentManager.apply { popBackStack() }.beginTransaction()
            .replace(R.id.container_for_fragments, FavoritesFragment.newInstance())
            .commit()
    }

    fun openMovie(movie: Movie) {
        activity.supportFragmentManager.beginTransaction()
            .replace(R.id.container_for_fragments, MovieFragment.newInstance(movie))
            .addToBackStack(null)
            .commit()
    }

    fun openHome() {
        activity.supportFragmentManager.apply { popBackStack() }.beginTransaction()
            .replace(R.id.container_for_fragments, MainFragment.newInstance())
            .commitNow()
    }

}