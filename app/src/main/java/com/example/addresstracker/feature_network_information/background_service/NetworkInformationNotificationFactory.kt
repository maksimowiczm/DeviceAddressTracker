package com.example.addresstracker.feature_network_information.background_service

import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.addresstracker.R
import com.example.addresstracker.feature_network_information.background_service.receiver.NetworkInformationNotificationActionReceiver
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation

class NetworkInformationNotificationFactory(
    private val context: Context,
) {
    fun create(networkInformation: INetworkInformation?): Notification {
        val notificationContent =
            "${context.resources.getString(R.string.tracker_notification_content)} ${networkInformation?.address}"

        val notificationBuilder = NotificationCompat
            .Builder(context, AddressTrackerService.NOTIFICATION_CHANNEL)
            .setContentTitle(context.getString(R.string.tracker_notification_title))
            .setContentText(notificationContent)
            .setSmallIcon(R.drawable.ic_notification_icon)
            .setOngoing(true)
            .setContentIntent(createContentIntent())
            .addAction(
                R.drawable.ic_notification_icon,
                context.getString(R.string.notification_stop), createStopAction()
            )

        return notificationBuilder.build()
    }

    private fun createContentIntent(): PendingIntent? {
        val intent = context.packageManager.getLaunchIntentForPackage(context.packageName)
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE)
    }

    private fun createStopAction(): PendingIntent? {
        val stopIntent = Intent(context, NetworkInformationNotificationActionReceiver::class.java)
        stopIntent.action = NetworkInformationNotificationActionReceiver.STOP

        return PendingIntent.getBroadcast(
            context,
            0,
            stopIntent,
            PendingIntent.FLAG_IMMUTABLE
        )
    }
}