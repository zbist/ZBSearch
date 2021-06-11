package com.example.notesfinal.zbsearch.ui.favorites

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.FavoritesFragmentBinding
import com.example.notesfinal.zbsearch.utils.viewBinding


class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private val viewModel: FavoritesViewModel by viewModels()

    private val binding: FavoritesFragmentBinding by viewBinding(FavoritesFragmentBinding::bind)


}