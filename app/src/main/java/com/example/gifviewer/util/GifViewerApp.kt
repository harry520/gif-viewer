package com.example.gifviewer.util

import android.app.Application
import com.tenor.android.core.network.ApiClient
import com.tenor.android.core.network.ApiService
import com.tenor.android.core.network.ApiService.IBuilder
import com.tenor.android.core.network.IApiClient

class GifViewerApp : Application() {

    companion object {
        const val KEY = "JNE35VJLOFWV"
    }

    override fun onCreate() {
        super.onCreate()
        val builder: IBuilder<IApiClient> = ApiService.Builder(this, IApiClient::class.java)
        builder.apiKey(KEY)
        ApiClient.init(this, builder)
    }

}