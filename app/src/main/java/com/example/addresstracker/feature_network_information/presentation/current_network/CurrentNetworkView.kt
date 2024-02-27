package com.example.addresstracker.feature_network_information.presentation.current_network

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.sharp.Check
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.Icon
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
    CurrentNetworkUI(currentNetwork = viewModel.currentNetwork?.toString())
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
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        Icon(
            imageVector = Icons.Sharp.Check,
            tint = Color.Green,
            contentDescription = currentNetwork,
            modifier = Modifier
                .weight(.2f)
                .fillMaxSize()
        )

        Column(Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.current_address),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = currentNetwork,
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun CurrentNetworkDisconnected() {
    Row(
        Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min)
    ) {

        Icon(
            imageVector = Icons.Sharp.Close,
            tint = Color.Red,
            contentDescription = stringResource(id = R.string.unable_to_get_public_address),
            modifier = Modifier
                .weight(.2f)
                .fillMaxSize()
        )

        Column(Modifier.weight(1f)) {
            Text(
                text = stringResource(id = R.string.current_address),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = stringResource(id = R.string.unable_to_get_public_address),
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                modifier = Modifier.fillMaxWidth(),
                color = Color.Red
            )
        }
    }
}

@Composable
@Preview
private fun CurrentNetworkViewConnectedPreview() {
    CurrentNetworkUI("83.6.50.62, 12:32, 26/02/24")
}

@Composable
@Preview
private fun CurrentNetworkViewDisconnectedPreview() {
    CurrentNetworkUI(null)
}
