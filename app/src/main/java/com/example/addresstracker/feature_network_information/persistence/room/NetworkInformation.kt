package com.example.addresstracker.feature_network_information.persistence.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import java.util.Date

@Entity
internal data class NetworkInformation(
    @PrimaryKey override val id: Int? = null,
    override val address: String,
    override val date: Date,
) : INetworkInformation

internal fun INetworkInformation.toNetworkInformation(): NetworkInformation {
    return NetworkInformation(
        this.id,
        this.address,
        this.date
    )
}