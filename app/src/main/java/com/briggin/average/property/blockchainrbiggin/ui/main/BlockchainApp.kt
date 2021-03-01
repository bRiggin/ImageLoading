package com.briggin.average.property.blockchainrbiggin.ui.main

import android.app.Application
import com.briggin.average.property.blockchainrbiggin.koinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BlockchainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BlockchainApp)
            modules(koinModule)
        }
    }
}