package com.github.muellerma.coffee

import android.content.Context
import android.os.Bundle
import android.service.voice.VoiceInteractionService
import android.service.voice.VoiceInteractionSession
import android.service.voice.VoiceInteractionSessionService
import android.util.Log
import com.roy.ForegroundService

private const val TAG = "Assist"

class InteractionSessionService : VoiceInteractionSessionService() {
    override fun onNewSession(bundle: Bundle?) = InteractionSession(this)
}

class InteractionSession(private val ctx: Context) : VoiceInteractionSession(ctx) {
    override fun onHandleAssist(state: AssistState) {
        Log.d(TAG, "onHandleAssist")
        ForegroundService.changeState(ctx, ForegroundService.Companion.STATE.TOGGLE, true)
        finish()
    }
}

class InteractionService : VoiceInteractionService()