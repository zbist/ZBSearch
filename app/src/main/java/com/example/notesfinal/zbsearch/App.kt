package com.example.notesfinal.zbsearch

import android.app.Application
import androidx.room.Room
import com.example.notesfinal.zbsearch.room.MovieDao
import com.example.notesfinal.zbsearch.room.MovieDatabase

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object {

        private var appInstance: App? = null
        private var db: MovieDatabase? = null
        private const val DB_NAME = "History.db"

        fun getHistoryDao(): MovieDao {
            if (db == null) {
                synchronized(MovieDatabase::class.java) {
                    if (db == null) {
                        if (appInstance == null) throw IllegalStateException("Application is null while creating DataBase")
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            MovieDatabase::class.java,
                            DB_NAME)
                            .allowMainThreadQueries()
                            .build()
                    }
                }
            }

            return db!!.movieDao()
        }
    }

}