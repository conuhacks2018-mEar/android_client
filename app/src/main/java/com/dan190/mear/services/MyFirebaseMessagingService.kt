package com.dan190.mear.services

import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import timber.log.Timber

/**
 * Created by Dan on 27/01/2018.
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {
    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        Timber.d("Message from %s: %s", remoteMessage?.from, remoteMessage?.data)
        val msgMap : Map<String, String> = remoteMessage?.data!!

        if (msgMap.isNotEmpty()) {
            Timber.d(msgMap.keys.toString())
        }

        // Get notification
        Timber.d("Notification body: %s", remoteMessage.notification?.body)
    }
}