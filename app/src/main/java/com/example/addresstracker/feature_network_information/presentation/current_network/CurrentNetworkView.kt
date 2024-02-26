package com.example.addresstracker.feature_network_information.presentation.current_network

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun CurrentNetworkView(
    viewModel: CurrentNetworkViewModel,
) {
    if (viewModel.currentNetwork == null) {
        return Text(text = "Address not found")
    }

    CurrentNetworkUI(
        currentNetwork = viewModel.currentNetwork.toString(),
        saveCurrentNetwork = { viewModel.saveCurrentNetwork() }
    )
}

@Composable
fun CurrentNetworkUI(
    currentNetwork: String,
    saveCurrentNetwork: () -> Unit,
) {

    Column {
        Text(
            text = "Current address",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(Alignment.CenterVertically),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = currentNetwork,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = { saveCurrentNetwork() },
            ) {
                Text(text = "Save")
            }
        }
    }
}

@Composable
@Preview
private fun CurrentNetworkViewPreview() {
    CurrentNetworkUI("83.6.50.62, 12:32, 26/02/24", {})
}
