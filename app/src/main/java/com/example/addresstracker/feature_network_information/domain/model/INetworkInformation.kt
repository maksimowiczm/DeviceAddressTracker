package com.example.addresstracker.feature_network_information.domain.model

import java.util.Date

interface INetworkInformation {
    val id: Int?
    val address: String
    val date: Date
}