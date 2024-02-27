package com.example.addresstracker.feature_network_information.background_service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.addresstracker.R
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.time.Duration
import javax.inject.Inject

@AndroidEntryPoint
class AddressTrackerService : Service() {

    private val serviceScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    @Inject
    lateinit var factory: INetworkInformationFactory

    @Inject
    lateinit var useCases: NetworkInformationUseCases

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> start()
            ACTION_STOP -> stop()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    private var addressTrackerReceiver: AddressTrackerReceiver? = null

    private fun start() {
        // todo use AlarmManager instead?
        serviceScope.launch {
            useCases.trackNetworkInformation.invoke(Duration.ofHours(6))
                .distinctUntilChanged { old, new ->
                    old?.address.equals(new?.address)
                }
                .onEach { networkInformation ->
                    updateNotification(networkInformation)

                    if (networkInformation != null) {
                        useCases.addNetworkInformationIfDifferentToMostRecent(networkInformation)
                    }
                }
                .launchIn(serviceScope)
        }

        addressTrackerReceiver =
            AddressTrackerReceiver(factory, useCases, { updateNotification(it) })
        application.registerReceiver(
            addressTrackerReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    private fun updateNotification(networkInformation: INetworkInformation?) {
        val notificationBuilder = NotificationCompat
            .Builder(this, NOTIFICATION_CHANNEL)
            .setContentTitle("Tracking address...")
            .setContentText("Address: null")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setOngoing(true)

        val updatedNotification =
            notificationBuilder.setContentText("Address ${networkInformation?.address}")

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notification = notificationBuilder.build()

        notificationManager.notify(1, updatedNotification.build())
        startForeground(1, notification)
    }

    private fun stop() {
        if (addressTrackerReceiver != null) {
            application.unregisterReceiver(addressTrackerReceiver)
        }
        stopForeground(true)
        stopSelf()
    }

    companion object {
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val NOTIFICATION_CHANNEL = "ADDRESS_TRACKER_CHANNEL"
    }
}