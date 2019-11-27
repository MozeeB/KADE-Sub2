package com.zeeb.footballmatchschedule

import android.app.Application
import com.orhanobut.hawk.Hawk
import com.zeeb.footballmatchschedule.di.myAppModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Hawk.init(this).build()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApp)
            modules(myAppModule)
        }
    }

}