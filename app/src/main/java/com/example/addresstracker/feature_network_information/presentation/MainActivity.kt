package com.example.addresstracker.feature_network_information.presentation

import android.Manifest
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.addresstracker.feature_network_information.presentation.current_network.CurrentNetworkView
import com.example.addresstracker.feature_network_information.presentation.current_network.CurrentNetworkViewModel
import com.example.addresstracker.feature_network_information.presentation.previous_networks.PreviousNetworksView
import com.example.addresstracker.feature_network_information.presentation.previous_networks.PreviousNetworksViewModel
import com.example.addresstracker.ui.theme.AddressTrackerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val currentNetworkViewModelVm: CurrentNetworkViewModel by viewModels()
    private val previousNetworksViewModel: PreviousNetworksViewModel by viewModels()

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.POST_NOTIFICATIONS,
            ),
            0
        )

        setContent {
            AddressTrackerTheme {
                Column(Modifier.fillMaxWidth()) {
                    Row(Modifier.fillMaxWidth()) {
                        CurrentNetworkView(viewModel = currentNetworkViewModelVm)
                    }
                    Spacer(Modifier.size(16.dp))
                    Row {
                        PreviousNetworksView(viewModel = previousNetworksViewModel)
                    }
                }
            }
        }
    }
}
