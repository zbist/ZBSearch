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
                    if (response.isSuccessful && response.body() != null) {
                        val movies = parseToMoviesList(response.body()!!)
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
                    if (response.isSuccessful && response.body() != null) {
                        val movies = parseToMoviesList(response.body()!!)
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


    //    private const val READ_TIMEOUT = 30_000
//
//    private val mainThreadHandler = Handler(Looper.getMainLooper())
//    private val gson = Gson()
//
//    override fun getNowPlayingMovies(
//        executor: Executor,
//        callback: (result: RepositoryResult<List<Movie>>) -> Unit
//    ) {
//
//        executor.execute {
//
//            val url =
//                URL("${BuildConfig.BASE_URL}/3/movie/now_playing?api_key=${BuildConfig.TMDB_MOVIES_API_KEY}&language=ru-RU&page=1")
//            val connection = url.openConnection() as HttpsURLConnection
//
//            try {
//                with(connection) {
//                    requestMethod = "GET"
//                    readTimeout = READ_TIMEOUT
//
//                    val response =
//                        gson.fromJson(inputStream.bufferedReader(), ResultMoviesTMDB::class.java)
//
//                    mainThreadHandler.post {
//                        callback.invoke(Success(parceToMoviesList(response)))
//                    }
//                }
//
//            } catch (exc: Exception) {
//
//                mainThreadHandler.post {
//                    callback.invoke(Error(exc))
//                }
//
//            } finally {
//                connection.disconnect()
//            }
//        }
//    }
//
//    override fun getUpcomingMovies(
//        executor: Executor,
//        callback: (result: RepositoryResult<List<Movie>>) -> Unit
//    ) {
//        executor.execute {
//
//            val url =
//                URL("${BuildConfig.BASE_URL}/3/movie/upcoming?api_key=${BuildConfig.TMDB_MOVIES_API_KEY}&language=ru-RU&page=1")
//            val connection = url.openConnection() as HttpsURLConnection
//
//            try {
//                with(connection) {
//                    requestMethod = "GET"
//                    readTimeout = READ_TIMEOUT
//
//                    val response =
//                        gson.fromJson(inputStream.bufferedReader(), ResultMoviesTMDB::class.java)
//
//                    parceToMoviesList(response)
//
//                    mainThreadHandler.post {
//                        callback.invoke(Success(parceToMoviesList(response)))
//                    }
//                }
//
//            } catch (exc: Exception) {
//
//                mainThreadHandler.post {
//                    callback.invoke(Error(exc))
//                }
//
//            } finally {
//                connection.disconnect()
//            }
//        }
//    }
//
    fun parseToMoviesList(response: ResultMoviesTMDB): List<Movie> {

        val listOfMovies = mutableListOf<Movie>()
        response.results.forEach {
            listOfMovies.add(
                Movie(
                    adult = it.adult,
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