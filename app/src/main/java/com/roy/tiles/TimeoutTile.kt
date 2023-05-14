package com.roy.tiles

import android.content.ComponentName
import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.roy.ForegroundService
import com.roy.Prefs
import com.roy.ServiceStatus
import com.roy.ServiceStatusObserver
import com.roy.coffeeApp

@RequiresApi(Build.VERSION_CODES.N)
class TimeoutTile : AbstractTile() {
    override fun onClick() {
//        Log.d(TAG, "onClick()")
        val prefs = Prefs(applicationContext)
        when {
            coffeeApp().lastStatusUpdate is ServiceStatus.Stopped -> {
                prefs.timeout = prefs.firstTimeout
                ForegroundService.changeState(this, ForegroundService.Companion.STATE.START, false)
            }

            prefs.nextTimeout == 0 -> {
                prefs.timeout = 0
                ForegroundService.changeState(this, ForegroundService.Companion.STATE.STOP, false)
            }

            else -> {
                prefs.timeout = prefs.nextTimeout
            }

        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    companion object {
        private val TAG = TimeoutTile::class.java.simpleName

        fun requestTileStateUpdate(context: Context) {
            Log.d(TAG, "requestTileStateUpdate()")
            try {
                requestListeningState(context, ComponentName(context, TimeoutTile::class.java))
            } catch (e: Exception) {
                Log.e(TAG, "Error when calling requestListeningState()", e)
            }
        }
    }

    class TileServiceStatusObserver(private val context: Context) : ServiceStatusObserver {
        override fun onServiceStatusUpdate(status: ServiceStatus) {
            requestTileStateUpdate(context)
        }
    }
}
