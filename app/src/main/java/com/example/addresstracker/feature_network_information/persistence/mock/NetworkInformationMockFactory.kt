package com.example.addresstracker.feature_network_information.persistence.mock

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository
import java.util.Calendar
import java.util.Date
import java.util.concurrent.ThreadLocalRandom
import javax.inject.Inject
import kotlin.random.Random
import kotlin.random.nextUInt


class NetworkInformationMockFactory @Inject constructor(
    private val repository: INetworkInformationRepository,
) : INetworkInformationFactory {
    override suspend fun createCurrentNetworkInformation(): INetworkInformation? {
        val random = ThreadLocalRandom.current()
            .nextLong(
                Calendar.getInstance().time.time - 1_000_000_000, Calendar.getInstance().time.time
            )

        val date = Date(random)
        val netInfo = object : INetworkInformation {
            override val id: Int
                get() = Random(0).nextInt()
            override val address: String
                get() = "${Random.nextUInt() % 255u}.${Random.nextUInt() % 255u}.${Random.nextUInt() % 255u}.${Random.nextUInt() % 255u}"
            override val date: Date
                get() = date
        }

        repository.insertAsync(netInfo)

        return netInfo
    }
}