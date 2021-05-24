package com.example.notesfinal.zbsearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.repository.IRepository
import com.example.notesfinal.zbsearch.repository.MockRepositoryImpl

class MainViewModel : ViewModel() {

    private val repository: IRepository = MockRepositoryImpl

    private val _moviesNowPlaying: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesNowPlaying: LiveData<List<Movie>> = _moviesNowPlaying

    private val _moviesUpcoming: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesUpcoming: LiveData<List<Movie>> = _moviesUpcoming

    fun fetchMovies(){
        _moviesNowPlaying.value = repository.getNowPlayingMovies()
        _moviesUpcoming.value = repository.getUpcomingMovies()
    }

}