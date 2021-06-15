package com.example.notesfinal.zbsearch.room

import androidx.room.*
import com.example.notesfinal.zbsearch.model.Movie
import java.util.concurrent.Executor

@Dao
interface MovieDao {

    @Query("SELECT * FROM MovieTable")
    suspend fun all(): List<MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun add(movie: MovieEntity)

    @Delete
    suspend fun delete(movie: MovieEntity)

}