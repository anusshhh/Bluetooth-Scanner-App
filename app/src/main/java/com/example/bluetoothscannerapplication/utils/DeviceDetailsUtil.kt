package com.example.bluetoothscannerapplication.utils

import android.annotation.SuppressLint
import android.bluetooth.BluetoothClass
import android.bluetooth.BluetoothDevice
import android.util.Log
import com.example.bluetoothscannerapplication.R

object DeviceDetailsUtil {

    @SuppressLint("MissingPermission")
    fun setDeviceTypeIcon(deviceType: Int)  : Int{
        return when(deviceType) {
            BluetoothClass.Device.Major.PHONE -> R.drawable.ic_phone_group
            BluetoothClass.Device.Major.COMPUTER -> R.drawable.ic_computer_group
            BluetoothClass.Device.Major.AUDIO_VIDEO -> R.drawable.ic_audio_video_group
            BluetoothClass.Device.Major.HEALTH ->R.drawable.ic_health_group
            else -> R.drawable.ic_unknown_group
        }
    }

    fun setBondStateString(bondState: Int): String {
        return when (bondState) {
            BluetoothDevice.BOND_NONE -> "Not Paired"
            BluetoothDevice.BOND_BONDING -> "Pairing in progress"
            BluetoothDevice.BOND_BONDED -> "Paired"
            else -> "Unknown Bond State"
        }
    }

    fun setDeviceTypeString(deviceClass : Int) : String {
        return when (deviceClass) {
            BluetoothClass.Device.Major.PHONE -> "Phone"
            BluetoothClass.Device.Major.COMPUTER -> "Computer"
            BluetoothClass.Device.Major.AUDIO_VIDEO -> "Audio/Video"
            BluetoothClass.Device.Major.HEALTH -> "Health"
            else -> "Unknown group"
        }
    }
}
