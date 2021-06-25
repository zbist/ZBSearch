package com.example.notesfinal.zbsearch.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.notesfinal.zbsearch.MainActivity
import com.example.notesfinal.zbsearch.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FcmService : FirebaseMessagingService() {

    companion object {
        private const val CHANNEL_ID = "CHANNEL_ID"
    }

    private val notificationManager by lazy {
        getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }

    override fun onCreate() {
        super.onCreate()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel(
                CHANNEL_ID,
                "Channel Name",
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = "Channel Description"
            }.also {
                notificationManager.createNotificationChannel(it)
            }
        }
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification?.apply(::showNotification)

    }

    private fun showNotification(notification: RemoteMessage.Notification) {

        val resultIntent = Intent(this, MainActivity::class.java)
            .apply {

            }

        val pendingIntent = PendingIntent.getActivities(
            this,
            0,
            arrayOf(resultIntent),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        NotificationCompat.Builder(this, CHANNEL_ID).apply {
            setContentTitle(notification.title)
            setContentText(notification.body)
            setSmallIcon(R.drawable.ic_star)
            setAutoCancel(true)
            setContentIntent(pendingIntent)
        }.also {
            notificationManager.notify(1, it.build())
        }
    }
}