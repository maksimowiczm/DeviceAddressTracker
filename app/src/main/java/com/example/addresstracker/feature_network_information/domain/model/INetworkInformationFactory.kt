package com.example.addresstracker.feature_network_information.domain.model

interface INetworkInformationFactory {
    suspend fun createCurrentNetworkInformation(): INetworkInformation?
}