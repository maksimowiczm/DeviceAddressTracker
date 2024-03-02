package com.example.addresstracker.feature_network_information.persistence.mock.dependency_injection

import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository
import com.example.addresstracker.feature_network_information.persistence.mock.NetworkInformationMockFactory
import com.example.addresstracker.feature_network_information.persistence.mock.NetworkInformationMockRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkInformationMockModule {
//    @Provides
//    @Singleton
    fun provideNetworkInformationRepository(): INetworkInformationRepository {
        return NetworkInformationMockRepository()
    }
//
//    @Provides
    fun provideNetworkInformationFactory(repository: INetworkInformationRepository): INetworkInformationFactory {
        return NetworkInformationMockFactory(repository)
    }
}