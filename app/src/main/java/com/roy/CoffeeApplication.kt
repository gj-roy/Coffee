package com.roy

import android.app.Application
import android.os.Build
import androidx.preference.PreferenceManager
import com.roy.tiles.TimeoutTile
import com.roy.tiles.ToggleTile
import com.google.android.material.color.DynamicColors

//TODO ad
//TODO firebase

//done
//ic launcher
//color
//ad id, internet permission in manifest
//leak canary
//rate app
//share app
//more app
//policy
//keystore
class CoffeeApplication : Application() {
    var observers = mutableListOf<ServiceStatusObserver>()
    var lastStatusUpdate: ServiceStatus = ServiceStatus.Stopped
        private set

    override fun onCreate() {
        super.onCreate()

        PreferenceManager.setDefaultValues(this, R.xml.db_pref_main, false)
        DynamicColors.applyToActivitiesIfAvailable(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            observers.add(ToggleTile.TileServiceStatusObserver(this))
            observers.add(TimeoutTile.TileServiceStatusObserver(this))
        }
    }

    fun notifyObservers(status: ServiceStatus) {
        lastStatusUpdate = status
        observers.forEach { observer ->
            observer.onServiceStatusUpdate(status)
        }
    }
}