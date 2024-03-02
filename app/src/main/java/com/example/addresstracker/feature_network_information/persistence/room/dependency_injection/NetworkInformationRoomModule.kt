package com.example.addresstracker.feature_network_information.persistence.room.dependency_injection

import android.content.Context
import androidx.room.Room
import com.example.addresstracker.feature_network_information.domain.model.INetworkInformationFactory
import com.example.addresstracker.feature_network_information.domain.repository.INetworkInformationRepository
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

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkInformationRoomModule {
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
    fun provideNetworkInformationFactory(@ApplicationContext context: Context): INetworkInformationFactory {
        return NetworkInformationOnlyWifiFactory(context)
    }
}