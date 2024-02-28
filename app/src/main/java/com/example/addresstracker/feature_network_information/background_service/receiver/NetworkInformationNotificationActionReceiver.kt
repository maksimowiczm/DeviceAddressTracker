package com.example.addresstracker.feature_network_information.background_service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.addresstracker.feature_network_information.background_service.AddressTrackerService

class NetworkInformationNotificationActionReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (context == null) {
            return
        }

        when (intent?.action) {
            STOP -> stopTrackerService(context)
        }
    }

    private fun stopTrackerService(context: Context) {
        Intent(context, AddressTrackerService::class.java).apply {
            action = AddressTrackerService.ACTION_STOP
            context.startService(this)
        }
    }

    companion object {
        const val STOP = "NETWORK_INFORMATION_NOTIFICATION_STOP"
    }
}