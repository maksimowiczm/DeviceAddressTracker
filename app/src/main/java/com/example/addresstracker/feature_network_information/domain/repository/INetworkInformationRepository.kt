package com.example.addresstracker.feature_network_information.domain.repository

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation

interface INetworkInformationRepository {
    suspend fun getAllAsync(): List<INetworkInformation>
    suspend fun getById(id: Int): INetworkInformation?
    suspend fun insertAsync(networkInformation: INetworkInformation)
    suspend fun deleteAsync(networkInformation: INetworkInformation)
    suspend fun getMostRecent(): INetworkInformation?
}