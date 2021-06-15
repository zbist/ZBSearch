package com.example.notesfinal.zbsearch.ui.movie

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.notesfinal.zbsearch.BuildConfig
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.MovieFragmentBinding
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.ui.main.MainFragment
import com.example.notesfinal.zbsearch.utils.viewBinding

class MovieFragment : Fragment(R.layout.movie_fragment) {

    companion object {
        fun newInstance(movie: Movie) = MovieFragment().apply {
            arguments = Bundle().apply {
                putParcelable(MainFragment.MOVIE, movie)
            }
        }
    }

    private val viewModel: MovieViewModel by viewModels()

    private val binding: MovieFragmentBinding by viewBinding(MovieFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movie = arguments?.getParcelable(MainFragment.MOVIE) ?: Movie()
        initMovie(movie)

        binding.addToFavorite.setOnClickListener {

            movie.isFavorite = !movie.isFavorite

            if (movie.isFavorite) {
                (it as ImageView).setImageResource(R.drawable.ic_on_favorite)
                viewModel.addToFavorite(movie)
            } else {
                (it as ImageView).setImageResource(R.drawable.ic_add_to_favorite)
                viewModel.deleteFromFavorite(movie)
            }

        }
    }

    private fun initMovie(movie: Movie) {
        with(binding) {
            title.text = movie.title
            englishTitle.text = movie.originalTitle
            genre.text = movie.genre
            rating.text = movie.voteAverage
            releaseDate.text = movie.releaseDate
            description.text = movie.overview

            Glide.with(poster)
                .load("${BuildConfig.IMG_URL}${movie.posterPath}")
                .into(poster)

            if (movie.isFavorite) {
                (addToFavorite as ImageView).setImageResource(R.drawable.ic_on_favorite)
            } else {
                (addToFavorite as ImageView).setImageResource(R.drawable.ic_add_to_favorite)
            }

        }
    }

}