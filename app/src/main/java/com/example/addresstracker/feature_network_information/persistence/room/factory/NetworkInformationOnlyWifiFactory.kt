package com.example.addresstracker.feature_network_information.persistence.room.factory

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.persistence.room.NetworkInformation
import com.example.addresstracker.utils.PublicAddressUtilities
import java.util.Calendar

internal class NetworkInformationOnlyWifiFactory(
    private val context: Context,
) : INetworkInformationFactory {
    override suspend fun createNetworkInformation(): INetworkInformation? {
        val connectivityManager = context
            .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val currentNetwork = connectivityManager.activeNetwork
        val caps = connectivityManager.getNetworkCapabilities(currentNetwork) ?: return null
        val isWifi = caps.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)

        if (!isWifi) {
            return null
        }

        val publicAddressUtilities = PublicAddressUtilities()
        val address = publicAddressUtilities.getPublicAddress().await() ?: return null
        val date = Calendar.getInstance().time

        return NetworkInformation(null, address, date)
    }
}