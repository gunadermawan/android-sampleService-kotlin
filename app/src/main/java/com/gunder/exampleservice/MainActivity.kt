package com.gunder.exampleservice

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gunder.exampleservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serviceIntent = Intent(this, BackgroundService::class.java)
        val foregroundServiceIntent = Intent(this, ForegroundService::class.java)
        binding.apply {
            btnStopBackgroundService.setOnClickListener {
                stopService(serviceIntent)
            }
            btnStartBackgroundService.setOnClickListener {
                startService(serviceIntent)
            }
            btnStartForegroundService.setOnClickListener {
                if (Build.VERSION.SDK_INT >= 26) {
                    startForegroundService(foregroundServiceIntent)
                } else {
                    startService(foregroundServiceIntent)
                }
            }
            btnStopForegroundService.setOnClickListener {
                stopService(foregroundServiceIntent)
            }
        }
    }
}