package com.example.bluetoothscannerapplication

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.ContextWrapper
import android.util.Log

@SuppressLint("MissingPermission") // Permission check will be done in the fragment and activities
class BluetoothService(context: Context) {
    private val mBluetoothManager: BluetoothManager
    private val mBluetoothAdapter: BluetoothAdapter

    init {
        mBluetoothManager =
            context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = mBluetoothManager.adapter
    }

    fun getAllPairedDevices(): Set<BluetoothDevice> {
        return mBluetoothAdapter.bondedDevices
    }

    fun unpairDevice(device: BluetoothDevice) {
        try {
            device::class.java.getMethod("removeBond").invoke(device)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}