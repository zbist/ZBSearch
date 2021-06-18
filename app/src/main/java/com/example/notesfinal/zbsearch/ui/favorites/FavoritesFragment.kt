package com.example.notesfinal.zbsearch.ui.favorites

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.FavoritesFragmentBinding
import com.example.notesfinal.zbsearch.utils.RouterHolder
import com.example.notesfinal.zbsearch.utils.viewBinding


class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var favoriteRecyclerView: RecyclerView
    private lateinit var favoriteMoviesAdapter: FavoritesAdapter

    private val viewModel: FavoritesViewModel by viewModels()

    private val binding: FavoritesFragmentBinding by viewBinding(FavoritesFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapterAndRV()

        viewModel.favoriteLiveData.observe(viewLifecycleOwner) {
            favoriteMoviesAdapter.listOfMovies = it
            favoriteMoviesAdapter.notifyDataSetChanged()
        }

        viewModel.fetchMovies()


    }

    private fun initAdapterAndRV() {
        favoriteMoviesAdapter = FavoritesAdapter {
            (activity as RouterHolder).mainRouter.openMovie(it)
        }

        favoriteRecyclerView = binding.favoriteRecyclerView

        favoriteRecyclerView.adapter = favoriteMoviesAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        favoriteRecyclerView.adapter = null
    }

}