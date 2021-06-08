package com.example.notesfinal.zbsearch.domain.repository

import com.example.notesfinal.zbsearch.domain.model.Movie
import java.util.concurrent.Executor

object MockRepositoryImpl : IRepository {

    override fun getNowPlayingMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    ) {
        callback.invoke(
            Success(
                listOf(
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie()
                )
            )
        )
    }

    override fun getUpcomingMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    ) {
        callback.invoke(
            Success(
                listOf(
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie(),
                    Movie()
                )
            )
        )
    }

    override fun getUpcomingMoviesWithService(): List<Movie> {
        return listOf(
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
            Movie(),
        )
    }

}