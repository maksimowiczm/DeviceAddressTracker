package com.example.addresstracker.feature_network_information.persistence.dependency_injection

import android.content.Context
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository
import com.example.addresstracker.feature_network_information.domain.use_case.AddNetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.AddNetworkInformationIfDifferentToMostRecent
import com.example.addresstracker.feature_network_information.domain.use_case.DeleteNetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.GetPreviousNetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import com.example.addresstracker.feature_network_information.domain.use_case.TrackNetworkInformation
import com.example.addresstracker.utils.DateStringifier
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkInformationModule {
    @Provides
    fun provideNetworkInformationUseCases(
        repository: INetworkInformationRepository,
        factory: INetworkInformationFactory,
    ): NetworkInformationUseCases {
        return NetworkInformationUseCases(
            addNetworkInformation = AddNetworkInformation(repository),
            deleteNetworkInformation = DeleteNetworkInformation(repository),
            getPreviousNetworkInformation = GetPreviousNetworkInformation(repository),
            trackNetworkInformation = TrackNetworkInformation(factory),
            addNetworkInformationIfDifferentToMostRecent =
            AddNetworkInformationIfDifferentToMostRecent(repository)
        )
    }

    @Provides
    fun provideNetworkInformationStringifier(@ApplicationContext context: Context): DateStringifier {
        return DateStringifier(context)
    }
}