package com.example.homework_1

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_1.LocalBroadcastReceiver.Companion.ACTION_SERVICE_FINISH
import com.example.homework_1.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    private val receiver = object : LocalBroadcastReceiver(){
            override fun onReceive(context: Context?, intent: Intent?) {
                if (intent?.action == ACTION_SERVICE_FINISH) {
                    val factorial =
                        intent.getLongExtra("text", 0)
                    val intent = Intent(this@SecondActivity, MainActivity::class.java)
                    intent.putExtra(
                        "factorial",
                        factorial
                    )
                    startActivity(intent)
                }
                if (intent?.action == ACTION_STEP) {
                    val progress = intent.getLongExtra("progress", 0)
                    binding.text.text = progress.toString()

                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var numberForFactorial =
            intent.getIntExtra("number for factorial", 0)

        binding.buttonSecondActivity.setOnClickListener {
            val intent = Intent(this, FactorialService::class.java).putExtra(
                "number for factorial",
                numberForFactorial
            )
            startService(intent)
            binding.buttonSecondActivity.isEnabled = false
        }

        val intentFilter = IntentFilter().apply {
            addAction(LocalBroadcastReceiver.ACTION_STEP)
            addAction(ACTION_SERVICE_FINISH)
        }
        registerReceiver(receiver, intentFilter)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(receiver)
    }
}