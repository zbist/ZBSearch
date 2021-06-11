package com.example.notesfinal.zbsearch.ui.ratings

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.RatingsFragmentBinding
import com.example.notesfinal.zbsearch.utils.viewBinding


class RatingsFragment : Fragment(R.layout.ratings_fragment) {

    companion object {
        fun newInstance() = RatingsFragment()
    }

    private val viewModel: RatingsViewModel by viewModels()

    private val binding: RatingsFragmentBinding by viewBinding(RatingsFragmentBinding::bind)

}