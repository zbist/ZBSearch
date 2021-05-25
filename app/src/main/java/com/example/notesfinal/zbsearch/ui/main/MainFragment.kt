package com.example.notesfinal.zbsearch.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.FavoritesFragmentBinding
import com.example.notesfinal.zbsearch.databinding.MainFragmentBinding
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.ui.RouterHolder
import com.example.notesfinal.zbsearch.ui.favorites.FavoritesViewModel
import com.example.notesfinal.zbsearch.ui.movie.MovieFragment
import com.example.notesfinal.zbsearch.viewBinding

class MainFragment() : Fragment(R.layout.main_fragment) {

    companion object {
        const val MOVIE = "MOVIE"
        fun newInstance() = MainFragment()
    }

    private lateinit var nowPlayingMoviesRecyclerView: RecyclerView
    private lateinit var nowPlayingMoviesAdapter: NowPlayingMoviesAdapter
    private lateinit var upcomingMoviesRecyclerView: RecyclerView
    private lateinit var upcomingMoviesAdapter: UpcomingMoviesAdapter

    private val viewModel: MainViewModel by viewModels()
    private val binding: MainFragmentBinding by viewBinding(MainFragmentBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            viewModel.fetchMovies()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        upcomingMoviesAdapter = UpcomingMoviesAdapter() {
            (activity as RouterHolder).mainRouter.openMovie(it)
        }
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter {
            (activity as RouterHolder).mainRouter.openMovie(it)
        }
        nowPlayingMoviesRecyclerView = binding.mainNowPlayingRecyclerView
        upcomingMoviesRecyclerView = binding.mainUpcomingRecyclerView

        nowPlayingMoviesRecyclerView.adapter = nowPlayingMoviesAdapter
        upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter

        viewModel.moviesNowPlaying.observe(viewLifecycleOwner) {
            nowPlayingMoviesAdapter.listOfMovies = it
            nowPlayingMoviesAdapter.notifyDataSetChanged()
        }

        viewModel.moviesUpcoming.observe(viewLifecycleOwner) {
            upcomingMoviesAdapter.listOfMovies = it
            upcomingMoviesAdapter.notifyDataSetChanged()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nowPlayingMoviesRecyclerView.adapter = null
        upcomingMoviesRecyclerView.adapter = null
    }

}