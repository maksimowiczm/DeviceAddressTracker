package com.example.addresstracker.feature_network_information.domain.use_case

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository

class AddNetworkInformationIfDifferentToMostRecent(private val repository: INetworkInformationRepository) {
    suspend operator fun invoke(networkInformation: INetworkInformation) {
        val mostRecent = repository.getMostRecent()

        if (mostRecent != null && mostRecent.address == networkInformation.address) {
            return
        }

        repository.insertAsync(networkInformation)
    }
}