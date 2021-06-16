package com.example.notesfinal.zbsearch.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesfinal.zbsearch.App
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.utils.parseFromMoviesToRoom
import com.example.notesfinal.zbsearch.utils.parseFromRoomToMovies
import kotlinx.coroutines.launch

class MovieViewModel : ViewModel() {

    private val repository = App.getHistoryDao()

    fun addToFavorite(movie: Movie) {

        viewModelScope.launch {
            repository.add(parseFromMoviesToRoom(movie))
        }
    }

    fun deleteFromFavorite(movie: Movie) {

        viewModelScope.launch {
            repository.delete(parseFromMoviesToRoom(movie))
        }
    }

}