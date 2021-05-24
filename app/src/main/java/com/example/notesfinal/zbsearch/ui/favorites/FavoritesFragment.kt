package com.example.notesfinal.zbsearch.ui.favorites

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.notesfinal.zbsearch.R
import com.example.notesfinal.zbsearch.databinding.FavoritesFragmentBinding
import com.example.notesfinal.zbsearch.viewBinding

class FavoritesFragment : Fragment(R.layout.favorites_fragment) {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private val viewModel: FavoritesViewModel by viewModels()

    private val binding: FavoritesFragmentBinding by viewBinding(FavoritesFragmentBinding::bind)


}