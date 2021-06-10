package com.example.notesfinal.zbsearch.repository

import com.example.notesfinal.zbsearch.model.Movie
import java.util.concurrent.Executor

interface IRepository {

    fun getNowPlayingMovies(callback: (result: RepositoryResult<List<Movie>>) -> Unit)

    fun getUpcomingMovies(callback: (result: RepositoryResult<List<Movie>>) -> Unit)
}

sealed class RepositoryResult<T>

data class Success<T>(val value: T) : RepositoryResult<T>()

data class Error<T>(val value: Throwable) : RepositoryResult<T>()