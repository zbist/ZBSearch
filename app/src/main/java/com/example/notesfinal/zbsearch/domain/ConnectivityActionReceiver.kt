package com.example.notesfinal.zbsearch.domain

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.notesfinal.zbsearch.R

class ConnectivityActionReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {


        Toast.makeText(context, context.getString(R.string.connection_changed), Toast.LENGTH_SHORT).show()
    }
}