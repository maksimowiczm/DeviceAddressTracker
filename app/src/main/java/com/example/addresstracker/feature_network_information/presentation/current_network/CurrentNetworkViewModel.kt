package com.example.addresstracker.feature_network_information.presentation.current_network

import android.app.Application
import android.content.Intent
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.addresstracker.feature_network_information.background_service.AddressTrackerService
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentNetworkViewModel @Inject constructor(
    private val application: Application,
    private val useCases: NetworkInformationUseCases,
    factory: INetworkInformationFactory,
) : AndroidViewModel(application) {

    var currentNetwork: INetworkInformation? by mutableStateOf(null)
        private set

    init {
        viewModelScope.launch {
            currentNetwork = factory.createCurrentNetworkInformation()
        }
    }

    fun saveCurrentNetwork() {
        viewModelScope.launch {
            currentNetwork?.let {
                useCases.addNetworkInformation(it)
            }
        }
    }

    fun startTracking() {
        val applicationContext = getApplication<Application>().applicationContext
        Intent(applicationContext, AddressTrackerService::class.java).apply {
            action = AddressTrackerService.ACTION_START
            application.startService(this)
        }
    }

    fun stopTracking() {
        val applicationContext = getApplication<Application>().applicationContext
        Intent(applicationContext, AddressTrackerService::class.java).apply {
            action = AddressTrackerService.ACTION_STOP
            application.startService(this)
        }
    }
}