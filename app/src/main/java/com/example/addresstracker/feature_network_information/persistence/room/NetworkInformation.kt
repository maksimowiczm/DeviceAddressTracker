package com.example.addresstracker.feature_network_information.persistence.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Entity
internal data class NetworkInformation(
    @PrimaryKey override val id: Int? = null,
    override val address: String,
    override val date: Date,
) : INetworkInformation {
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("hh:mm, dd/MM/yy", Locale.getDefault())
        val date = dateFormat.format(date)

        return "${address}, $date"
    }
}

internal fun INetworkInformation.toNetworkInformation(): NetworkInformation {
    return NetworkInformation(
        this.id,
        this.address,
        this.date
    )
}