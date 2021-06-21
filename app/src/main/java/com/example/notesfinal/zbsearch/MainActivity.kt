package com.example.notesfinal.zbsearch

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.example.notesfinal.zbsearch.utils.MainRouter
import com.example.notesfinal.zbsearch.utils.RouterHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(R.layout.main_activity), RouterHolder {

    override val mainRouter = MainRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        initBottomNavigationMenu()
        if (savedInstanceState == null) {
            mainRouter.openHome()
        }
    }

    val locationPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                mainRouter.openRatings()
            } else {
                Toast.makeText(
                    this,
                    "Too sad, you cannot use this amazing feature!",
                    Toast.LENGTH_LONG
                ).show()
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
                        if (ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.ACCESS_FINE_LOCATION
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {

                            mainRouter.openRatings()
                        } else {
                            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                                    Snackbar.make(
                                        findViewById(R.id.container),
                                        "Need permissions!",
                                        Snackbar.LENGTH_INDEFINITE
                                    ).setAction("Grant") {
                                        locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                                    }.show()
                            } else {
                                locationPermissionRequest.launch(Manifest.permission.ACCESS_FINE_LOCATION)
                            }
                        }
                        true
                    }
                    else -> false
                }
            }
    }
}