package com.example.addresstracker.feature_network_information.presentation.network_tracker

import android.Manifest
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.addresstracker.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@Composable
fun NetworkTrackerView(
    viewModel: NetworkTrackerViewModel,
) {
    NetworkTrackerUI(
        { viewModel.startTracking() },
        { viewModel.stopTracking() },
        { viewModel.allowNotifications() }
    )
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun NetworkTrackerUI(
    startTracking: () -> Unit,
    stopTracking: () -> Unit,
    allowNotifications: () -> Unit,
) {

    val state = rememberPermissionState(Manifest.permission.POST_NOTIFICATIONS)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(Alignment.CenterVertically)
    ) {

        when {
            state.status.isGranted -> {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp),
                    onClick = startTracking
                ) {
                    Text(text = stringResource(id = R.string.start_tracking))
                }

                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp),
                    onClick = stopTracking
                ) {
                    Text(text = stringResource(id = R.string.stop_tracking))
                }
            }

            else -> {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(5.dp),
                    onClick = allowNotifications
                ) {
                    Text(text = stringResource(id = R.string.allow_notification_button))
                }
            }
        }
    }
}

@Composable
@Preview
fun NetworkTrackerViewPreview() = NetworkTrackerUI({}, {}, {})