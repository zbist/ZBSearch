package com.example.notesfinal.zbsearch.ui.ratings

import android.annotation.SuppressLint
import android.location.Geocoder
import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.notesfinal.zbsearch.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import java.io.IOException

class MapsFragment : Fragment(R.layout.fragment_maps) {

    private lateinit var btn: Button
    private lateinit var search: EditText
    private lateinit var addr: TextView

    @SuppressLint("MissingPermission")
    private val callback = OnMapReadyCallback { googleMap ->

        val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))

        googleMap.setOnMapLongClickListener { location ->
            context?.let {
                val geoCoder = Geocoder(it)
                Thread {
                    try {
                        val addresses =
                            geoCoder.getFromLocation(location.latitude, location.longitude, 1)
                        addr.post { addr.text = addresses[0].getAddressLine(0) }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }.start()
            }
        }

        googleMap.isMyLocationEnabled = true

        btn.setOnClickListener {
            val geoCoder = Geocoder(it.context)
            val searchText = search.text.toString()
            Thread {
                try {
                    val addresses = geoCoder.getFromLocationName(searchText, 1)
                    if (addresses.size > 0) {
                        val location = LatLng(
                            addresses[0].latitude,
                            addresses[0].longitude
                        )
                        it.post {
                            googleMap.moveCamera(
                                CameraUpdateFactory.newLatLngZoom(
                                    location,
                                    15f
                                )
                            )
                        }
                    }
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }.start()
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        initViews(view)


    }

    private fun initViews(view: View) {
        search = view.findViewById(R.id.search_text)
        btn = view.findViewById(R.id.search_btn)
        addr = view.findViewById(R.id.adress)
    }
}

