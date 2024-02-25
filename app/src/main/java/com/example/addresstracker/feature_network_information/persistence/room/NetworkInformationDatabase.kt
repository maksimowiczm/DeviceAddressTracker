package com.example.addresstracker.feature_network_information.persistence.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(
    entities = [NetworkInformation::class],
    version = 1
)
@TypeConverters(DateConverter::class)
abstract class NetworkInformationDatabase : RoomDatabase() {
    abstract val networkInformationDao: INetworkInformationDao

    companion object {
        const val DATABASE_NAME = "networkInformation_db"
    }
}