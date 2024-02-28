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

class AddressTrackerNetworkUpdateReceiver(
    private val networkInformationFactory: INetworkInformationFactory,
    private val useCases: NetworkInformationUseCases,
    private val onReceive: (INetworkInformation?) -> Unit,
) : BroadcastReceiver() {

    @OptIn(DelicateCoroutinesApi::class)
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action != CONNECTIVITY_ACTION) {
            return
        }

        GlobalScope.launch {
            val netInfo = networkInformationFactory.createCurrentNetworkInformation()
            if (netInfo != null) {
                useCases.addNetworkInformationIfDifferentToMostRecent(netInfo)
            }
            onReceive(netInfo)
        }
    }
}