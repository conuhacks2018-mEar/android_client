package com.dan190.mear.services

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.support.v4.app.NotificationCompat
import com.dan190.mear.MyApplication
import com.dan190.mear.R
import com.dan190.mear.views.main.MainActivity

/**
 * Created by Dan on 27/01/2018.
 */
object MyNotification {
    fun createNotificationChannel(context: Context?, channelName: String?, channelDescription: String?) {
        // Notification
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager

        // id of the channel
        val id = MyApplication.getInstance().getString(R.string.mear_channel_id)

        // user-visible name of channel
        val name = channelName

        // user-visible description of channel
        val description = channelDescription
        val importance = NotificationManager.IMPORTANCE_HIGH
        val channel = NotificationChannel(id, name, importance)

        // Configure the notification channel
        channel.description = description
        channel.enableLights(true)

        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        channel.lightColor = Color.RED;
        channel.enableVibration(true)
        channel.vibrationPattern = longArrayOf(100, 100, 500)
        notificationManager?.createNotificationChannel(channel)
    }

    fun createNotification(context: Context?, title: String?, text: String?) {
        val builder = context?.let { NotificationCompat.Builder(it, it.getString(R.string.mear_channel_id)!!) }
        builder?.setSmallIcon(R.drawable.announcement)
                ?.setContentTitle(title)
                ?.setContentText(text)

        // Make it open Main Activity
        val intent = Intent(context, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT)
        builder?.setContentIntent(pendingIntent)

        val notificationID = 1
        val notificationManager = context?.getSystemService(Context.NOTIFICATION_SERVICE) as? NotificationManager
        notificationManager?.notify(notificationID, builder?.build())
    }
}