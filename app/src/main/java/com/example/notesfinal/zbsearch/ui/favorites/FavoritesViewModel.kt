package com.example.notesfinal.zbsearch.ui.favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesfinal.zbsearch.App
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.utils.parseFromRoomToMovies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.Executors

class FavoritesViewModel : ViewModel() {

    private val repository = App.getHistoryDao()

    private val _favoriteLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val favoriteLiveData: LiveData<List<Movie>> = _favoriteLiveData

    fun fetchMovies() {

        viewModelScope.launch(Dispatchers.IO) {
            var movies = repository.all()
            _favoriteLiveData.postValue(parseFromRoomToMovies(movies))
        }
    }

}