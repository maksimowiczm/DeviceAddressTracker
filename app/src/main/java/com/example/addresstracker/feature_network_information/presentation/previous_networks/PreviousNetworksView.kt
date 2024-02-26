package com.example.addresstracker.feature_network_information.presentation.previous_networks

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun PreviousNetworksView(
    viewModel: PreviousNetworksViewModel,
) {
    viewModel.previousNetworks?.map { network ->
        Text(text = network.toString())
    }
}