package com.example.notesfinal.zbsearch.ui.movie

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.MovieFragmentBinding
import com.example.notesfinal.zbsearch.domain.model.Movie
import com.example.notesfinal.zbsearch.ui.main.MainFragment
import com.example.notesfinal.zbsearch.viewBinding

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
    }

    private fun initMovie(movie: Movie) {
        with(binding) {
            title.text = movie.title
            englishTitle.text = movie.originalTitle
            poster.setImageResource(R.mipmap.ic_launcher)
            genre.text = movie.genre
            rating.text = movie.voteAverage
            releaseDate.text = movie.releaseDate
            description.text = movie.overview
        }
    }

}