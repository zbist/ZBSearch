package com.example.notesfinal.zbsearch.domain.repository

import android.os.Handler
import android.os.Looper
import com.example.notesfinal.zbsearch.BuildConfig
import com.example.notesfinal.zbsearch.domain.model.Movie
import com.example.notesfinal.zbsearch.domain.model.responses.ResultMoviesTMDB
import com.google.gson.Gson
import java.lang.Exception
import java.net.URL
import java.util.concurrent.Executor
import javax.net.ssl.HttpsURLConnection

object RepositoryTMDBImpl : IRepository {

    private const val READ_TIMEOUT = 30_000

    private const val URL_MAIN = "https://api.themoviedb.org/3/movie/"

    private val mainThreadHandler = Handler(Looper.getMainLooper())
    private val gson = Gson()

    override fun getNowPlayingMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    ) {

        executor.execute {

            val url =
                URL("${URL_MAIN}now_playing?api_key=${BuildConfig.TMDB_MOVIES_API_KEY}&language=ru-RU&page=1")
            val connection = url.openConnection() as HttpsURLConnection

            try {
                with(connection) {
                    requestMethod = "GET"
                    readTimeout = READ_TIMEOUT

                    val response =
                        gson.fromJson(inputStream.bufferedReader(), ResultMoviesTMDB::class.java)

                    mainThreadHandler.post {
                        callback.invoke(Success(parceToMoviesList(response)))
                    }
                }

            } catch (exc: Exception) {

                mainThreadHandler.post {
                    callback.invoke(Error(exc))
                }

            } finally {
                connection.disconnect()
            }
        }
    }

    override fun getUpcomingMovies(
        executor: Executor,
        callback: (result: RepositoryResult<List<Movie>>) -> Unit
    ) {
        executor.execute {

            val url =
                URL("${URL_MAIN}upcoming?api_key=${BuildConfig.TMDB_MOVIES_API_KEY}&language=ru-RU&page=1")
            val connection = url.openConnection() as HttpsURLConnection

            try {
                with(connection) {
                    requestMethod = "GET"
                    readTimeout = READ_TIMEOUT

                    val response =
                        gson.fromJson(inputStream.bufferedReader(), ResultMoviesTMDB::class.java)

                    parceToMoviesList(response)

                    mainThreadHandler.post {
                        callback.invoke(Success(parceToMoviesList(response)))
                    }
                }

            } catch (exc: Exception) {

                mainThreadHandler.post {
                    callback.invoke(Error(exc))
                }

            } finally {
                connection.disconnect()
            }
        }
    }

    override fun getUpcomingMoviesWithService(): List<Movie> {
        val url =
            URL("${URL_MAIN}upcoming?api_key=${BuildConfig.TMDB_MOVIES_API_KEY}&language=ru-RU&page=1")
        val connection = url.openConnection() as HttpsURLConnection

        try {
            with(connection) {
                requestMethod = "GET"
                readTimeout = READ_TIMEOUT

                val response =
                    gson.fromJson(inputStream.bufferedReader(), ResultMoviesTMDB::class.java)

                return parceToMoviesList(response)
            }
        } catch (exc: Exception) {
            return emptyList()
        } finally {
            connection.disconnect()
        }
    }

    fun parceToMoviesList(response: ResultMoviesTMDB): List<Movie> {

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