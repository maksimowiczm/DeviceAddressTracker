package com.example.addresstracker.feature_network_information.domain.use_case

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository

class DeleteNetworkInformation(private val repository: INetworkInformationRepository) {
    suspend operator fun invoke(networkInformation: INetworkInformation) {
        repository.deleteAsync(networkInformation)
    }
}