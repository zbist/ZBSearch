package com.example.notesfinal.zbsearch.repository

import com.example.notesfinal.zbsearch.model.Movie

interface IRepository {

    fun getNowPlayingMovies() : List<Movie>
    fun getUpcomingMovies() : List<Movie>
}