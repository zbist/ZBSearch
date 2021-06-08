package com.example.notesfinal.zbsearch.domain

import android.app.IntentService
import android.content.Intent
import com.example.notesfinal.zbsearch.domain.model.Movie
import com.example.notesfinal.zbsearch.domain.repository.IRepository
import com.example.notesfinal.zbsearch.domain.repository.RepositoryTMDBImpl
import com.example.notesfinal.zbsearch.domain.repository.Success

class GetUpcomingService : IntentService("Get Weather") {

    companion object {
        const val ACTION = "ACTION"
        const val DESCRIPTION = "DESCRIPTION"
    }

    private val repository: IRepository = RepositoryTMDBImpl

    override fun onHandleIntent(intent: Intent?) {
        val result = repository.getUpcomingMoviesWithService()
        val intent = Intent(ACTION).apply {
            putExtra(DESCRIPTION, result.first())
        }.also {
            sendBroadcast(it)
        }
    }
}