package com.example.addresstracker.feature_network_information.background_service

import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import com.example.addresstracker.feature_network_information.background_service.receiver.AddressTrackerNetworkUpdateReceiver
import com.example.addresstracker.feature_network_information.domain.Settings
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
    lateinit var networkInformationFactory: INetworkInformationFactory

    @Inject
    lateinit var useCases: NetworkInformationUseCases

    override fun onBind(intent: Intent?): IBinder? = null

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> startService()
            ACTION_STOP -> stopService()
        }
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onDestroy() {
        super.onDestroy()
        serviceScope.cancel()
    }

    private var addressTrackerNetworkUpdateReceiver: AddressTrackerNetworkUpdateReceiver? = null

    private fun startService() {
        // if receiver is setup do not do this again
        if (addressTrackerNetworkUpdateReceiver != null) {
            return
        }

        // todo use AlarmManager instead?
        serviceScope.launch {
            useCases.trackNetworkInformation.invoke(Duration.ofHours(Settings.UPDATE_PERIOD))
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

        addressTrackerNetworkUpdateReceiver =
            AddressTrackerNetworkUpdateReceiver(networkInformationFactory, useCases) {
                updateNotification(it)
            }.also {
                it.registerSelf(this)
            }
    }

    private val notificationFactory = NetworkInformationNotificationFactory(this)

    private fun updateNotification(networkInformation: INetworkInformation?) {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val notification = notificationFactory.create(networkInformation)

        notificationManager.notify(1, notification)
        startForeground(1, notification)
    }

    private fun stopService() {
        if (addressTrackerNetworkUpdateReceiver != null) {
            unregisterReceiver(addressTrackerNetworkUpdateReceiver)
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