package com.example.notesfinal.zbsearch.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.repository.*
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class MainViewModel : ViewModel() {

    private val executor = Executors.newSingleThreadExecutor()

    private val repository: IRepository = RepositoryTMDBImpl

    private val _moviesNowPlayingLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesNowPlayingLiveData: LiveData<List<Movie>> = _moviesNowPlayingLiveData

    private val _moviesUpcomingLiveData: MutableLiveData<List<Movie>> = MutableLiveData()
    val moviesUpcomingLiveData: LiveData<List<Movie>> = _moviesUpcomingLiveData

    private val _swipeProgressLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val swipeProgressLiveData: LiveData<Boolean> = _swipeProgressLiveData

    private val _errorCheckLiveData: MutableLiveData<Boolean> = MutableLiveData(false)
    val errorCheckLiveData: LiveData<Boolean> = _errorCheckLiveData

    fun fetchMovies() {
        _swipeProgressLiveData.value = true
        repository.getNowPlayingMovies(executor, {
            when (it){
                is Success -> {
                    _moviesNowPlayingLiveData.value = it.value
                }

                is Error -> {
                    _errorCheckLiveData.value = true
                }
            }
        })
        repository.getUpcomingMovies(executor, {
            when (it){
                is Success -> {
                    _moviesUpcomingLiveData.value = it.value
                }

                is Error -> {
                    _errorCheckLiveData.value = true
                }
            }
        })
        _errorCheckLiveData.value = false
        _swipeProgressLiveData.value = false
    }

    override fun onCleared() {
        super.onCleared()
        executor.shutdown()
    }
}