package com.example.bluetoothscannerapplication.receivers

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bluetoothscannerapplication.extensions.checkBluetoothPermission
import com.example.bluetoothscannerapplication.extensions.makeShortToast


class BluetoothScanReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val action = intent!!.action
        if (BluetoothDevice.ACTION_FOUND == action) {
            //bluetooth device found
            val device =
                intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE) as BluetoothDevice?
            Log.e("TAG", "onReceive: ${device?.name}")
        }
    }

}

