package com.example.notesfinal.zbsearch.utils

import com.example.notesfinal.zbsearch.model.Movie
import com.example.notesfinal.zbsearch.model.responses.ResultMoviesTMDB
import com.example.notesfinal.zbsearch.room.MovieEntity

fun parseFromRoomToMovies(results: List<MovieEntity>): List<Movie> {

    val listOfMovies = mutableListOf<Movie>()
    results.forEach {
        listOfMovies.add(
            Movie(
                id = it.id,
                title = it.title,
                originalTitle = it.originalTitle,
                genre = it.genre,
                posterPath = it.posterPath,
                voteAverage = it.voteAverage,
                releaseDate = it.releaseDate,
                overview = it.overview,
                isFavorite = it.isFavorite.toBoolean()
            )
        )
    }
    return listOfMovies
}

fun parseFromMoviesToRoom(movie: Movie) =
    MovieEntity(
        movie.id,
        movie.title,
        movie.originalTitle,
        movie.genre,
        movie.posterPath,
        movie.voteAverage,
        movie.releaseDate,
        movie.overview,
        movie.isFavorite.toString()
    )