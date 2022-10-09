package com.gunder.exampleservice

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*

class BoundService : Service() {
    private var binder = MyBinder()
    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)
    val numberLiveData: MutableLiveData<Int> = MutableLiveData()

    override fun onBind(intent: Intent): IBinder {
        Log.d(TAG, "onBind")
        serviceScope.launch {
            for (i in 1..50) {
                delay(1000)
                Log.d(TAG, "Do something $i")
                numberLiveData.postValue(i)
            }
            Log.d(TAG, "bound service stopped!")
        }
        return binder
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(TAG, "onUnbind:")
        serviceJob.cancel()
        return super.onUnbind(intent)
    }

    internal inner class MyBinder : Binder() {
        val getService: BoundService = this@BoundService
    }

    companion object {
        private val TAG = BoundService::class.java.simpleName
    }
}

