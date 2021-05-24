package com.example.notesfinal.zbsearch.repository

import com.example.notesfinal.zbsearch.model.Movie

object MockRepositoryImpl : IRepository{
    override fun getNowPlayingMovies(): List<Movie> {
        return listOf(Movie(), Movie(), Movie(), Movie(), Movie(), Movie(), Movie(), Movie())
    }

    override fun getUpcomingMovies(): List<Movie> {
        return listOf(Movie(), Movie(), Movie(), Movie(), Movie(), Movie(), Movie(), Movie())
    }
}