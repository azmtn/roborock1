package com.example.homework_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homework_1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factorial = intent.getLongExtra("factorial", 0)

        binding.buttonMainActivity.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra(
                "number for factorial",
                NUMBER_FOR_FACTORIAL
            )
            startActivity(intent)
        }
        binding.textView.text = "is $factorial"
    }

    companion object {
        const val NUMBER_FOR_FACTORIAL = 20
    }
}