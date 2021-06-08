package com.example.notesfinal.zbsearch.domain.repository

import com.example.notesfinal.zbsearch.domain.model.Movie
import java.util.concurrent.Executor

interface IRepository {

    fun getNowPlayingMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    )

    fun getUpcomingMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    )

    fun getUpcomingMoviesWithService(): List<Movie>
}