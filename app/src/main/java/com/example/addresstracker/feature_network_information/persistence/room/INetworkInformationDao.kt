package com.example.addresstracker.feature_network_information.persistence.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface INetworkInformationDao {

    @Query("SELECT * FROM networkinformation")
    suspend fun getAllAsync(): List<NetworkInformation>

    @Query("SELECT * FROM networkinformation WHERE id = :id")
    suspend fun getById(id: Int): NetworkInformation?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAsync(networkInformation: NetworkInformation)

    @Delete
    suspend fun deleteAsync(networkInformation: NetworkInformation)
}