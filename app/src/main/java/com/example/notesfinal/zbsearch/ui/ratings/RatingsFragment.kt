package com.example.notesfinal.zbsearch.ui.ratings

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.MovieFragmentBinding
import com.example.notesfinal.zbsearch.databinding.RatingsFragmentBinding
import com.example.notesfinal.zbsearch.ui.movie.MovieViewModel
import com.example.notesfinal.zbsearch.viewBinding

class RatingsFragment : Fragment(R.layout.ratings_fragment) {

    companion object {
        fun newInstance() = RatingsFragment()
    }

    private val viewModel: RatingsViewModel by viewModels()

    private val binding: RatingsFragmentBinding by viewBinding(RatingsFragmentBinding::bind)

}