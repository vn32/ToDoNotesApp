package com.example.todonotesapp.fcm

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingServices:FirebaseMessagingService(){
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        Log.d("Firebase",message.from)
        Log.d("Firebase",message.notification?.body)
    }

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }
}