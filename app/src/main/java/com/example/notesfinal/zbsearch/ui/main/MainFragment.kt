package com.example.notesfinal.zbsearch.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.MainFragmentBinding
import com.example.notesfinal.zbsearch.RouterHolder
import com.example.notesfinal.zbsearch.domain.GetUpcomingService
import com.example.notesfinal.zbsearch.domain.model.Movie
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
            requireContext().startService(Intent(requireContext(), GetUpcomingService::class.java))
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdaptersAndRV()

        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.fetchMovies()
            viewModel.swipeProgressLiveData.observe(viewLifecycleOwner) {
                binding.swipeToRefresh.isRefreshing = it
            }
        }

        viewModel.moviesNowPlayingLiveData.observe(viewLifecycleOwner) {
            nowPlayingMoviesAdapter.listOfMovies = it
            nowPlayingMoviesAdapter.notifyDataSetChanged()
        }

        viewModel.moviesUpcomingLiveData.observe(viewLifecycleOwner) {
            upcomingMoviesAdapter.listOfMovies = it
            upcomingMoviesAdapter.notifyDataSetChanged()
        }

        viewModel.errorCheckLiveData.observe(viewLifecycleOwner) {
            if (it) {
                Toast.makeText(context, R.string.error_text, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initAdaptersAndRV() {
        upcomingMoviesAdapter = UpcomingMoviesAdapter {
            (activity as RouterHolder).mainRouter.openMovie(it)
        }
        nowPlayingMoviesAdapter = NowPlayingMoviesAdapter {
            (activity as RouterHolder).mainRouter.openMovie(it)
        }

        nowPlayingMoviesRecyclerView = binding.mainNowPlayingRecyclerView
        upcomingMoviesRecyclerView = binding.mainUpcomingRecyclerView

        nowPlayingMoviesRecyclerView.adapter = nowPlayingMoviesAdapter
        upcomingMoviesRecyclerView.adapter = upcomingMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        nowPlayingMoviesRecyclerView.adapter = null
        upcomingMoviesRecyclerView.adapter = null
    }

    private val upcomingDescriptionReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context?, intent: Intent?) {
            val result = intent?.getParcelableExtra<Movie>(GetUpcomingService.DESCRIPTION)!!
            upcomingMoviesAdapter.listOfMovies = listOf(result)
            upcomingMoviesAdapter.notifyDataSetChanged()
        }
    }

    override fun onStart() {
        super.onStart()

        IntentFilter(GetUpcomingService.ACTION).also {
            requireActivity().registerReceiver(upcomingDescriptionReceiver, it)
        }
    }

    override fun onStop() {
        super.onStop()
        requireActivity().unregisterReceiver(upcomingDescriptionReceiver)
    }

}