package com.example.notesfinal.zbsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notesfinal.zbsearch.ui.favorites.FavoritesFragment
import com.example.notesfinal.zbsearch.ui.main.MainFragment
import com.example.notesfinal.zbsearch.ui.ratings.RatingsFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        setSupportActionBar(findViewById(R.id.toolbar))
        initBottomNavigationMenu()
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container_for_fragments, MainFragment.newInstance())
                .commitNow()
        }
    }

    private fun initBottomNavigationMenu() {
        findViewById<BottomNavigationView>(R.id.bottom_navigation_menu)
            .setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.navigation_favorites -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_for_fragments, FavoritesFragment.newInstance())
                            .commit()
                        true
                    }
                    R.id.navigation_home -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_for_fragments, MainFragment.newInstance())
                            .commit()
                        true
                    }
                    R.id.navigation_ratings -> {
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.container_for_fragments, RatingsFragment.newInstance())
                            .commit()
                        true
                    }
                    else -> false
                }
            }
    }
}