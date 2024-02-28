package com.example.addresstracker.feature_network_information.presentation.current_network

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.addresstracker.R

@Composable
fun CurrentNetworkView(viewModel: CurrentNetworkViewModel) {
    CurrentNetworkUI(currentNetwork = viewModel.currentNetwork?.address)
}

@Composable
fun CurrentNetworkUI(currentNetwork: String?) {
    if (currentNetwork == null) {
        CurrentNetworkDisconnected()
    } else {
        CurrentNetworkConnected(currentNetwork)
    }
}

@Composable
fun CurrentNetworkConnected(currentNetwork: String) {
    Text(
        text = currentNetwork,
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Green
    )
}

@Composable
fun CurrentNetworkDisconnected() {
    Text(
        text = stringResource(id = R.string.unable_to_get_public_address),
        textAlign = TextAlign.Center,
        fontSize = 20.sp,
        modifier = Modifier.fillMaxWidth(),
        color = Color.Red
    )
}

@Composable
@Preview
private fun CurrentNetworkViewConnectedPreview() {
    CurrentNetworkUI("83.6.50.62")
}

@Composable
@Preview
private fun CurrentNetworkViewDisconnectedPreview() {
    CurrentNetworkUI(null)
}
