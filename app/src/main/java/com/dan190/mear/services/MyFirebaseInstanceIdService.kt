package com.dan190.mear.services

import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService
import timber.log.Timber

/**
 * Created by Dan on 27/01/2018.
 */
class MyFirebaseInstanceIdService : FirebaseInstanceIdService() {
    override fun onTokenRefresh() {
        val refreshedToken = FirebaseInstanceId.getInstance().token
        Timber.d("Refreshed token %s", refreshedToken)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        if (refreshedToken != null) {
            sendRegistrationToServer(refreshedToken)
        }
    }

    fun sendRegistrationToServer(token: String) {
        val database = FirebaseDatabase.getInstance()
        val policeRef = database.getReference("police")
        policeRef.setValue(token)
        Timber.d("sent token %s to firebase", token)
    }
}