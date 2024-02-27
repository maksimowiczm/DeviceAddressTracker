package com.example.addresstracker.feature_network_information.presentation.network_tracker

import android.app.Application
import android.content.Intent
import androidx.lifecycle.AndroidViewModel
import com.example.addresstracker.feature_network_information.background_service.AddressTrackerService

class NetworkTrackerViewModel(
    private val application: Application,
) : AndroidViewModel(application) {

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