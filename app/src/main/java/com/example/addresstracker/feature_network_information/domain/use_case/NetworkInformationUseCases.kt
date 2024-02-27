package com.example.addresstracker.feature_network_information.domain.use_case

data class NetworkInformationUseCases(
    val addNetworkInformation: AddNetworkInformation,
    val deleteNetworkInformation: DeleteNetworkInformation,
    val getPreviousNetworkInformation: GetPreviousNetworkInformation,
    val trackNetworkInformation: TrackNetworkInformation,
    val addNetworkInformationIfDifferentToMostRecent: AddNetworkInformationIfDifferentToMostRecent,
)