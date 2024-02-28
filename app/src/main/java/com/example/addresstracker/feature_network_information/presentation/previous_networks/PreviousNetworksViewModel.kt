package com.example.addresstracker.feature_network_information.presentation.previous_networks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import com.example.addresstracker.utils.DateStringifier
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreviousNetworksViewModel @Inject constructor(
    private val useCases: NetworkInformationUseCases,
    val stringifier: DateStringifier,
) : ViewModel() {
    var previousNetworks: List<INetworkInformation>? by mutableStateOf(null)
        private set

    init {
        refresh()
    }

    fun refresh() {
        viewModelScope.launch {
            previousNetworks = useCases.getPreviousNetworkInformation()
        }
    }
}