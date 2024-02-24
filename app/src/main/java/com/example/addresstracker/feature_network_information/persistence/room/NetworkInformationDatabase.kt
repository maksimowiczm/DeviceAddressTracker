package com.example.addresstracker.feature_network_information.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [NetworkInformation::class],
    version = 1
)
abstract class NetworkInformationDatabase : RoomDatabase() {
    abstract val networkInformationDao: INetworkInformationDao

    companion object {
        const val DATABASE_NAME = "networkInformation_db"
    }
}