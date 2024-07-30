package com.mujapps.mvisample

import android.app.Application
import com.mujapps.mvisample.di.articleModule
import com.mujapps.mvisample.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MviApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
    }

    private fun initKoin() {
        val requiredModules = networkModule + articleModule

        startKoin {
            androidContext(this@MviApplication)
            modules(requiredModules)
        }
    }
}