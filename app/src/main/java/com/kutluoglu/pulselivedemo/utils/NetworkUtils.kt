package com.kutluoglu.pulselivedemo.utils

import android.content.Context
import android.net.ConnectivityManager
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-26.
 *
 */

class NetworkUtils @Inject constructor (
    private val context: Context
) {
    fun hasInternet() : Boolean {
        val connectivityManager: ConnectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo

        return networkInfo != null && networkInfo.isConnected
    }

    fun hasNotInternet() = !hasInternet()
}