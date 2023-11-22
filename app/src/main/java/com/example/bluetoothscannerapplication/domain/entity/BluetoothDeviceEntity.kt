package com.example.bluetoothscannerapplication.domain.entity

import android.bluetooth.BluetoothDevice
import androidx.annotation.DrawableRes

data class BluetoothDeviceEntity(
    @DrawableRes val icon: Int,
    val deviceName: String,
    val deviceAddress: String,
    val deviceBondState: String,
    val deviceType: String
) {
    fun unpairDevice(device: BluetoothDevice ) {
        try {
            val method = device::class.java.getMethod("removeBond")
            method.invoke(device)
        } catch (e: Exception) {
            e.printStackTrace()
            // Handle exceptions, such as NoSuchMethodException or IllegalAccessException
        }
    }

}