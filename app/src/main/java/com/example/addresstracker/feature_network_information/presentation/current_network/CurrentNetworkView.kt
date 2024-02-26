package com.example.addresstracker.feature_network_information.presentation.current_network

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun CurrentNetworkView(
    viewModel: CurrentNetworkViewModel,
) {
    if (viewModel.currentNetwork == null) {
        return Text(text = "Address not found")
    }

    val dateFormat = SimpleDateFormat("hh:mm, dd/MM/yy", Locale.getDefault())
    val date = dateFormat.format(viewModel.currentNetwork!!.date)

    val output = "${viewModel.currentNetwork!!.address}, $date"

    CurrentNetworkUI(
        currentNetwork = output,
        saveCurrentNetwork = { viewModel.saveCurrentNetwork() }
    )
}

@Composable
fun CurrentNetworkUI(
    currentNetwork: String,
    saveCurrentNetwork: () -> Unit,
) {

    Row(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {
        Text(
            text = currentNetwork,
            modifier = Modifier.weight(1f)
        )
        Button(
            onClick = { saveCurrentNetwork() },
        ) {
            Text(text = "Save")
        }
    }
}

@Composable
@Preview
fun CurrentNetworkViewPreview() {
    CurrentNetworkUI("83.6.50.62, 12:32, 26/02/24", {})
}
