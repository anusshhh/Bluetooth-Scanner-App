package com.example.bluetoothscannerapplication

import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.content.Intent
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult


@SuppressLint("MissingPermission") // Permission check will be done in the fragment and activities
class BluetoothService(context: Context) {
    private val mBluetoothManager: BluetoothManager
    private val mBluetoothAdapter: BluetoothAdapter
    private val REQUEST_ENABLE_BLUETOOTH = 1
    private val REQUEST_DISABLE_BLUETOOTH = 2

    init {
        mBluetoothManager =
            context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        mBluetoothAdapter = mBluetoothManager.adapter
    }

    fun isBluetoothEnabled() :Boolean {
        return mBluetoothAdapter.isEnabled
    }

    fun enableBluetooth():Intent {
        return  Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
    }

    fun disableBluetooth() {
        mBluetoothAdapter.disable()
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

    fun startDiscovery(){
        mBluetoothAdapter.startDiscovery()
    }
}