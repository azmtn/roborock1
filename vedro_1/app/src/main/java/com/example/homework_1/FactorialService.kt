package com.example.homework_1

import android.app.Service
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.*

class FactorialService : Service() {

    private val coroutineScope = CoroutineScope(Dispatchers.Main)

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        var numberForFactorial: Int = intent?.getIntExtra("number for factorial", 0) ?: 0

        coroutineScope.launch {
            var myResult: Long = 1
            for (i in 1..numberForFactorial) {
                delay(50)
                myResult *= i.toLong()
                Intent(LocalBroadcastReceiver.ACTION_STEP).apply {
                    putExtra("progress", myResult)
                    sendBroadcast(this)
                }
            }
            Intent(LocalBroadcastReceiver.ACTION_SERVICE_FINISH).apply {
                putExtra("text", myResult)
                sendBroadcast(this)
            }
            stopSelf()
        }
        return START_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        coroutineScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}