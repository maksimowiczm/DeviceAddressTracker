package com.example.addresstracker.utils

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.net.URL

class PublicAddressUtilities {
    suspend fun getPublicAddress(): Deferred<String?> {
        return coroutineScope {
            async(Dispatchers.IO) {
                val url = URL("https://api.ipify.org")
                val httpsURLConnection = url.openConnection()
                val iStream = httpsURLConnection.getInputStream()
                val buff = ByteArray(1024)
                val read = iStream.read(buff)

                return@async String(buff, 0, read)
            }
        }
    }
}