package com.example.notesfinal.zbsearch

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesfinal.zbsearch.domain.ConnectivityActionReceiver
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(R.layout.main_activity), RouterHolder {

    private val receiver : BroadcastReceiver = ConnectivityActionReceiver()

    override val mainRouter = MainRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))

        initBottomNavigationMenu()

        registerReceiver(receiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION))

        if (savedInstanceState == null) {
            mainRouter.openHome()
        }
    }

    private fun initBottomNavigationMenu() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
            .setOnNavigationItemSelectedListener { it ->
                when (it.itemId) {
                    R.id.navigation_favorites -> {
                        mainRouter.openFavorites()
                        true
                    }
                    R.id.navigation_home -> {
                        mainRouter.openHome()
                        true
                    }
                    R.id.navigation_ratings -> {
                        mainRouter.openRatings()
                        true
                    }
                    else -> false
                }
            }
    }

    override fun onDestroy() {
        unregisterReceiver(receiver)
        super.onDestroy()
    }
}