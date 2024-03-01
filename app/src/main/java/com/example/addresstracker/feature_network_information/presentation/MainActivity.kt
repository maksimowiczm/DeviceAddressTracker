package com.example.addresstracker.feature_network_information.presentation

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.addresstracker.feature_network_information.background_service.receiver.AddressTrackerNetworkUpdateReceiver
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import com.example.addresstracker.feature_network_information.presentation.current_network.CurrentNetworkView
import com.example.addresstracker.feature_network_information.presentation.current_network.CurrentNetworkViewModel
import com.example.addresstracker.feature_network_information.presentation.network_tracker.NetworkTrackerView
import com.example.addresstracker.feature_network_information.presentation.network_tracker.NetworkTrackerViewModel
import com.example.addresstracker.feature_network_information.presentation.previous_networks.PreviousNetworksView
import com.example.addresstracker.feature_network_information.presentation.previous_networks.PreviousNetworksViewModel
import com.example.addresstracker.ui.theme.AddressTrackerTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val currentNetworkViewModelVm: CurrentNetworkViewModel by viewModels()
    private val previousNetworksViewModel: PreviousNetworksViewModel by viewModels()
    private val networkTrackerViewModel: NetworkTrackerViewModel by viewModels()

    @Inject
    lateinit var networkInformationFactory: INetworkInformationFactory

    @Inject
    lateinit var useCases: NetworkInformationUseCases

    private var networkUpdateReceiver: AddressTrackerNetworkUpdateReceiver? = null

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        networkTrackerViewModel.settingsIntent.observe(this) {
            if (it != null) {
                startActivity(it)
            }
        }

        networkUpdateReceiver =
            AddressTrackerNetworkUpdateReceiver(networkInformationFactory, useCases) {
                currentNetworkViewModelVm.refresh()
                previousNetworksViewModel.refresh()
            }
                .also {
                    it.registerSelf(this)
                }


        setContent {
            AddressTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.surface
                ) {
                    Column(Modifier.fillMaxWidth()) {
                        Row(Modifier.fillMaxWidth()) {
                            CurrentNetworkView(viewModel = currentNetworkViewModelVm)
                        }
                        Row(modifier = Modifier.weight(1f)) {
                            PreviousNetworksView(viewModel = previousNetworksViewModel)
                        }
                        Row {
                            NetworkTrackerView(viewModel = networkTrackerViewModel)
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        if (networkUpdateReceiver != null) {
            unregisterReceiver(networkUpdateReceiver)
        }
        super.onDestroy()
    }
}
