package com.example.notesfinal.zbsearch.ui.movie

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.MainFragmentBinding
import com.example.notesfinal.zbsearch.databinding.MovieFragmentBinding
import com.example.notesfinal.zbsearch.ui.main.MainViewModel
import com.example.notesfinal.zbsearch.viewBinding

class MovieFragment : Fragment(R.layout.movie_fragment) {

    companion object {
        fun newInstance() = MovieFragment()
    }

    private val viewModel: MovieViewModel by viewModels()

    private val binding: MovieFragmentBinding by viewBinding(MovieFragmentBinding::bind)

}