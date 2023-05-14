package com.roy

interface ServiceStatusObserver {
    fun onServiceStatusUpdate(status: ServiceStatus)
}
