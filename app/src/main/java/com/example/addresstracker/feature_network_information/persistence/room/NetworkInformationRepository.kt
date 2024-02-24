package com.example.addresstracker.feature_network_information.persistence.room

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository

class NetworkInformationRepository(
    private val dao: INetworkInformationDao,
) : INetworkInformationRepository {

    override suspend fun getAllAsync(): List<INetworkInformation> = dao.getAllAsync()

    override suspend fun getById(id: Int): INetworkInformation? = dao.getById(id)

    override suspend fun insertAsync(networkInformation: INetworkInformation) =
        dao.insertAsync(networkInformation.toNetworkInformation())

    override suspend fun deleteAsync(networkInformation: INetworkInformation) =
        dao.deleteAsync(networkInformation.toNetworkInformation())
}