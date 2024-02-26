package com.example.addresstracker.feature_network_information.persistence.room.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository
import com.example.addresstracker.feature_network_information.domain.use_case.AddNetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.DeleteNetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.GetPreviousNetworkInformation
import com.example.addresstracker.feature_network_information.domain.use_case.NetworkInformationUseCases
import com.example.addresstracker.feature_network_information.persistence.room.DateConverter
import com.example.addresstracker.feature_network_information.persistence.room.NetworkInformationDatabase
import com.example.addresstracker.feature_network_information.persistence.room.NetworkInformationRepository
import com.example.addresstracker.feature_network_information.persistence.room.factory.NetworkInformationOnlyWifiFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

// todo make some kind of DI abstraction
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkInformationModule {
    @Provides
    @Singleton
    fun provideNetworkInformationDatabase(@ApplicationContext context: Context): NetworkInformationDatabase {
        return Room
            .databaseBuilder(
                context,
                NetworkInformationDatabase::class.java,
                NetworkInformationDatabase.DATABASE_NAME
            )
            .addTypeConverter(DateConverter()) // doesn't work without it 💀
            .build()
    }

    @Provides
    @Singleton
    fun provideNetworkInformationRepository(database: NetworkInformationDatabase): INetworkInformationRepository {
        return NetworkInformationRepository(database.networkInformationDao)
    }

    @Provides
    @Singleton
    fun provideNetworkInformationFactory(@ApplicationContext context: Context): INetworkInformationFactory {
        return NetworkInformationOnlyWifiFactory(context)
    }

    @Provides
    @Singleton
    fun provideNetworkInformationUseCases(repository: INetworkInformationRepository): NetworkInformationUseCases {
        return NetworkInformationUseCases(
            addNetworkInformation = AddNetworkInformation(repository),
            deleteNetworkInformation = DeleteNetworkInformation(repository),
            getPreviousNetworkInformation = GetPreviousNetworkInformation(repository)
        )
    }
}