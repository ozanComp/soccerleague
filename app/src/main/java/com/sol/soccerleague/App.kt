package com.sol.soccerleague

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import com.sol.soccerleague.di.component.AppComponent
import com.sol.soccerleague.di.component.DaggerAppComponent
import com.sol.soccerleague.di.module.ApiModule
import com.sol.soccerleague.di.module.RoomModule

class App : Application() , LifecycleObserver {
    companion object {
        lateinit var instance: App
    }
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        @Suppress("DEPRECATION")
        appComponent = DaggerAppComponent
            .builder()
            .roomModule(RoomModule(this))
            .apiModule(ApiModule(this))
            .build()
    }
}