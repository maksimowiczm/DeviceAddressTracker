package com.example.addresstracker.feature_network_information.background_service

import android.app.Notification
import android.content.Context
import androidx.core.app.NotificationCompat
import com.example.addresstracker.R
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation

class NetworkInformationNotificationFactory(
    private val context: Context,
) {
    fun create(networkInformation: INetworkInformation?): Notification {
        val notificationBuilder = NotificationCompat
            .Builder(context, AddressTrackerService.NOTIFICATION_CHANNEL)
            .setContentTitle("Tracking address...")
            .setContentText("Address: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)

        val notification =
            notificationBuilder.setContentText("Address ${networkInformation?.address}")

        return notification.build()
    }
}