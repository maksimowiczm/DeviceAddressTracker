package com.example.addresstracker.feature_network_information.presentation.previous_networks

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.persistence.room.NetworkInformation
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun PreviousNetworksView(
    viewModel: PreviousNetworksViewModel,
) {
    viewModel.previousNetworks?.let {
        PreviousNetworksUI(
            networks = it,
            dateStringifier = { date -> viewModel.stringifier.stringifyWithCurrentLocale(date) }
        )
    }
}

@Composable
fun PreviousNetworksUI(
    networks: List<INetworkInformation>,
    dateStringifier: (Date) -> String,
) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        itemsIndexed(networks) { i, network ->
            Row(
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(
                    textAlign = TextAlign.Center,
                    text = "${network.address}, ",
                )

                Text(
                    textAlign = TextAlign.Center,
                    text = dateStringifier(network.date),
                )
            }
            if (i < networks.lastIndex) {
                Divider(
                    thickness = 1.dp,
                    color = Color.Black
                )
            }
        }
    }
}

@Composable
@Preview
private fun PreviousNetworksViewPreview() {
    var networks: Array<INetworkInformation> = arrayOf()

    for (i in 1..20) {
        networks += NetworkInformation(
            address = "192.168.1.$i",
            date = Calendar.getInstance().time
        )
    }

    PreviousNetworksUI(
        networks.toList()
    ) { date ->
        val dateFormat = SimpleDateFormat("hh:mm, dd/MM/yy", Locale.getDefault())
        dateFormat.format(date)
    }
}