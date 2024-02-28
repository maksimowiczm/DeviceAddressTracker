package com.example.addresstracker.feature_network_information.presentation.current_network

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrentNetworkViewModel @Inject constructor(
    private val factory: INetworkInformationFactory,
) : ViewModel() {

    var currentNetwork: INetworkInformation? by mutableStateOf(null)
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            currentNetwork = factory.createCurrentNetworkInformation()
        }
    }
}