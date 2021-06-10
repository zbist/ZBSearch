package com.example.notesfinal.zbsearch.model.network

import com.example.notesfinal.zbsearch.BuildConfig
import com.example.notesfinal.zbsearch.model.responses.ResultMoviesTMDB
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesAPI {

    @GET("3/movie/now_playing")
    fun getNowPlayingMovies(
        @Query("api_key") key: String,
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1,
    ): Call<ResultMoviesTMDB>

    @GET("3/movie/upcoming")
    fun getUpcomingMovies(
        @Query("api_key") key: String,
        @Query("language") language: String = "ru-RU",
        @Query("page") page: Int = 1,
    ): Call<ResultMoviesTMDB>
}