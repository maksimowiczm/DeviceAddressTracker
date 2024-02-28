package com.example.addresstracker.feature_network_information.background_service.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager.CONNECTIVITY_ACTION
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlin.coroutines.EmptyCoroutineContext


class AddressTrackerNetworkUpdateReceiver(
    private val factory: INetworkInformationFactory,
    private val useCases: NetworkInformationUseCases,
    private val notificationUpdater: (INetworkInformation?) -> Unit,
) : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != CONNECTIVITY_ACTION) {
            return
        }

        @OptIn(DelicateCoroutinesApi::class)
        GlobalScope.launch(EmptyCoroutineContext) {
            val netInfo = factory.createCurrentNetworkInformation()
            if (netInfo != null) {
                useCases.addNetworkInformationIfDifferentToMostRecent(netInfo)
            }

            notificationUpdater(netInfo)
        }
    }
}