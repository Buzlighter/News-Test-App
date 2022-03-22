package com.test.newsapp

import android.app.Application
import com.test.newsapp.api.RetrofitClient

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        RetrofitClient.configureClient()
    }
}