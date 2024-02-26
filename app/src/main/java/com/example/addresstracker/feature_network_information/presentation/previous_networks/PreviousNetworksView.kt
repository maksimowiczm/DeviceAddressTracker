package com.example.addresstracker.feature_network_information.presentation.previous_networks

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.persistence.room.NetworkInformation
import java.util.Calendar

@Composable
fun PreviousNetworksView(viewModel: PreviousNetworksViewModel) {
    viewModel.previousNetworks?.let { PreviousNetworksUI(networks = it) }
}

@Composable
fun PreviousNetworksUI(networks: List<INetworkInformation>) {
    LazyColumn(Modifier.fillMaxSize()) {
        itemsIndexed(networks) { i, network ->
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                textAlign = TextAlign.Center,
                text = network.toString()
            )
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

    for (i in 1..255) {
        networks += NetworkInformation(
            address = "192.168.1.$i",
            date = Calendar.getInstance().time
        )
    }

    PreviousNetworksUI(networks.toList())
}