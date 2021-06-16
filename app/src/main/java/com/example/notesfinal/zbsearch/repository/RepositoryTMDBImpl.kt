package com.example.notesfinal.zbsearch.repository

import com.example.notesfinal.zbsearch.BuildConfig
import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.model.network.MoviesAPI
import com.example.notesfinal.zbsearch.model.responses.ResultMoviesTMDB
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RepositoryTMDBImpl : IRepository {

    private val moviesAPI = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(MoviesAPI::class.java)

    override fun getNowPlayingMovies(callback: (result: RepositoryResult<List<Movie>>) -> Unit) {
        moviesAPI.getNowPlayingMovies(key = BuildConfig.TMDB_MOVIES_API_KEY)
            .enqueue(object : Callback<ResultMoviesTMDB> {

                override fun onResponse(
                    call: Call<ResultMoviesTMDB>,
                    response: Response<ResultMoviesTMDB>
                ) {
                    if (response.isSuccessful) {
                        val movies = parseToMoviesList(response.body())
                        callback.invoke(Success(movies))
                    } else {
                        callback.invoke(Error(Throwable(response.code().toString())))
                    }
                }

                override fun onFailure(call: Call<ResultMoviesTMDB>, t: Throwable) {
                    callback.invoke(Error(t))
                }
            })
    }

    override fun getUpcomingMovies(callback: (result: RepositoryResult<List<Movie>>) -> Unit) {
        moviesAPI.getUpcomingMovies(key = BuildConfig.TMDB_MOVIES_API_KEY)
            .enqueue(object : Callback<ResultMoviesTMDB> {

                override fun onResponse(
                    call: Call<ResultMoviesTMDB>,
                    response: Response<ResultMoviesTMDB>
                ) {
                    if (response.isSuccessful) {
                        val movies = parseToMoviesList(response.body())
                        callback.invoke(Success(movies))
                    } else {
                        callback.invoke(Error(Throwable(response.code().toString())))
                    }
                }

                override fun onFailure(call: Call<ResultMoviesTMDB>, t: Throwable) {
                    callback.invoke(Error(t))
                }
            })
    }

    fun parseToMoviesList(response: ResultMoviesTMDB?): List<Movie> {

        val listOfMovies = mutableListOf<Movie>()
        response?.results?.forEach {
            listOfMovies.add(
                Movie(
                    id = it.id,
                    title = it.title,
                    originalTitle = it.originalTitle,
                    genre = it.genreIds.toString(),
                    posterPath = it.posterPath,
                    voteAverage = it.voteAverage.toString(),
                    releaseDate = it.releaseDate,
                    overview = it.overview,
                )
            )
        }
        return listOfMovies
    }
}