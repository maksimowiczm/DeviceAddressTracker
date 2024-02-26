package com.example.addresstracker.feature_network_information.domain.use_case

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.time.delay
import java.time.Duration

class TrackNetworkInformation(
    private val factory: INetworkInformationFactory,
) {
    suspend operator fun invoke(period: Duration): Flow<INetworkInformation?> =
        flow {
            while (true) {
                emit(factory.createCurrentNetworkInformation())
                delay(period)
            }
        }
}