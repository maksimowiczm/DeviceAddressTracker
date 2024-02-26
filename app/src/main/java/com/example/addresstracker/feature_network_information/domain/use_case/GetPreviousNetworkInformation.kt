package com.example.addresstracker.feature_network_information.domain.use_case

import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository

class GetPreviousNetworkInformation(private val repository: INetworkInformationRepository) {
    suspend operator fun invoke() = repository.getAllAsync()
}