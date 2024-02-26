package com.example.addresstracker

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import com.example.addresstracker.feature_network_information.background_service.AddressTrackerService
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class AddressTrackerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        val channel = NotificationChannel(
            AddressTrackerService.NOTIFICATION_CHANNEL,
            "AddressTracker",
            NotificationManager.IMPORTANCE_LOW
        )
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }
}