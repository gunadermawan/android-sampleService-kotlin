package com.gunder.exampleservice

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.appcompat.app.AppCompatActivity
import com.gunder.exampleservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var boundStatus = false
    private lateinit var boundService: BoundService

    //    bound service
    private val connection = object : ServiceConnection {
        override fun onServiceConnected(name: ComponentName?, service: IBinder?) {
            val myBinder = service as BoundService.MyBinder
            boundService = myBinder.getService
            boundStatus = true
            getNumberFromService()
        }

        override fun onServiceDisconnected(p0: ComponentName?) {
            boundStatus = false
        }
    }

    private fun getNumberFromService() {
        boundService.numberLiveData.observe(this) { number ->
            binding.tvBoundServiceNumber.text = number.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val serviceIntent = Intent(this, BackgroundService::class.java)
        val foregroundServiceIntent = Intent(this, ForegroundService::class.java)
        val boundServiceIntent = Intent(this, BoundService::class.java)
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
            btnStartBoundService.setOnClickListener {
                bindService(boundServiceIntent, connection, BIND_AUTO_CREATE)
            }
            btnStopBoundService.setOnClickListener {
                unbindService(connection)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        if (boundStatus) {
            unbindService(connection)
            boundStatus = false
        }
    }
}