package com.example.notesfinal.zbsearch.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.repository.IRepository
import com.example.notesfinal.zbsearch.repository.MockRepositoryImpl

class MainViewModel : ViewModel() {

    private val repository: IRepository = MockRepositoryImpl

    private val _moviesNowPlayingLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesNowPlayingLiveData: LiveData<List<Movie>> = _moviesNowPlayingLiveData

    private val _moviesUpcomingLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesUpcomingLiveData: LiveData<List<Movie>> = _moviesUpcomingLiveData

    private val _swipeProgressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val swipeProgressLiveData: LiveData<Boolean> = _swipeProgressLiveData

    fun fetchMovies(){
        _swipeProgressLiveData.value = true
        _moviesNowPlayingLiveData.value = repository.getNowPlayingMovies()
        _moviesUpcomingLiveData.value = repository.getUpcomingMovies()
        _swipeProgressLiveData.value = false
    }

}