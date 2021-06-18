package com.example.notesfinal.zbsearch

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat
import com.example.notesfinal.zbsearch.utils.MainRouter
import com.example.notesfinal.zbsearch.utils.RouterHolder
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity(R.layout.main_activity), RouterHolder {

    private val contactsPermissionRequest =
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

    override val mainRouter = MainRouter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setSupportActionBar(findViewById(R.id.toolbar))
        initBottomNavigationMenu()
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

                        if (ContextCompat.checkSelfPermission(
                                this,
                                Manifest.permission.READ_CONTACTS
                            ) == PackageManager.PERMISSION_GRANTED
                        ) {

                            mainRouter.openRatings()
                            true
                        } else {
                            if (shouldShowRequestPermissionRationale(Manifest.permission.READ_CONTACTS)) {
                                Snackbar.make(
                                    findViewById(R.id.container),
                                    "Need permissions!",
                                    Snackbar.LENGTH_INDEFINITE
                                ).setAction("Grant") {
                                    contactsPermissionRequest.launch(Manifest.permission.READ_CONTACTS)
                                }.show()
                            } else {
                                contactsPermissionRequest.launch(Manifest.permission.READ_CONTACTS)
                            }
                            false
                        }
                    }
                    else -> false
                }
            }
    }
}