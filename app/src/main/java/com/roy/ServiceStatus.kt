package com.roy

import kotlin.time.Duration

sealed class ServiceStatus {
    class Running(val remaining: Duration?) : ServiceStatus() {
        override fun toString() = "${Running::class.java.simpleName}(${remaining?.inWholeSeconds})"
    }

    object Stopped : ServiceStatus() {
        override fun toString(): String = Stopped::class.java.simpleName
    }
}
