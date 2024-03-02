package com.example.addresstracker.feature_network_information.persistence.mock

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository

class NetworkInformationMockRepository : INetworkInformationRepository {
    private var networks: Array<INetworkInformation> = emptyArray()

    override suspend fun getAllAsync(): List<INetworkInformation> = networks.sortedBy { it.date }

    override suspend fun getById(id: Int): INetworkInformation? = networks.find { it.id == id }

    override suspend fun insertAsync(networkInformation: INetworkInformation) {
        networks += networkInformation
    }

    override suspend fun deleteAsync(networkInformation: INetworkInformation) = Unit

    override suspend fun getMostRecent(): INetworkInformation? {
        return networks.maxByOrNull { it.date }
    }
}