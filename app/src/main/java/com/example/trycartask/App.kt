package com.example.trycartask

import android.util.Log.DEBUG
import androidx.multidex.MultiDexApplication
import com.example.trycartask.BuildConfig.DEBUG
import io.ak1.nytimes.di.databaseModule
import io.ak1.nytimes.di.networkModule
import io.ak1.nytimes.di.repoModule
import io.ak1.nytimes.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import java.util.logging.Level


class App : MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            //inject Android context
            androidContext(applicationContext)
            // use Android logger - Level.INFO by default
             // androidLogger(Level.DEBUG)
            koin.loadModules(listOf(databaseModule, networkModule, viewModelModule, repoModule))

        }
    }
}