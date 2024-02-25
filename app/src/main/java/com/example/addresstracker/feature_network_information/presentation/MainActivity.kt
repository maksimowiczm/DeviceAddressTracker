package com.example.addresstracker.feature_network_information.presentation

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.lifecycleScope
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.persistence.room.factory.NetworkInformationOnlyWifiFactory
import com.example.addresstracker.ui.theme.AddressTrackerTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = NetworkInformationOnlyWifiFactory(applicationContext)

        lifecycleScope.launch {
            val netInfo = factory.createNetworkInformation()!!

            Log.d("netInfo", netInfo.toString())

            setContent {
                AddressTrackerTheme {
                    // A surface container using the 'background' color from the theme
                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        NetworkInformationComposable(netInfo)
                    }
                }
            }
        }
    }
}

@Composable
fun NetworkInformationComposable(networkInformation: INetworkInformation) {
    Text(
        text = networkInformation.toString(),
    )
}