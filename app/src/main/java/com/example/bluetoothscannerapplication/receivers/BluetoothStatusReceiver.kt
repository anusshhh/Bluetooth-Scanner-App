package com.example.bluetoothscannerapplication.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.bluetoothscannerapplication.BluetoothService


class BluetoothStatusReceiver(private val callback: (Boolean) -> Unit) : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        callback(BluetoothService(context).isBluetoothEnabled())
    }
}
