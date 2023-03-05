package com.example.homework_1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

open class LocalBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        when (intent?.action) {
            ACTION_STEP -> {}
            ACTION_SERVICE_FINISH -> {}
        }
    }

    companion object {
        const val ACTION_STEP = "step"
        const val ACTION_SERVICE_FINISH = "finish"
    }
}
