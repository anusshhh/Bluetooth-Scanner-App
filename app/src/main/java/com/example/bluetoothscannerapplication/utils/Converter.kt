package com.example.bluetoothscannerapplication.utils

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.util.Log
import com.example.bluetoothscannerapplication.utils.DeviceDetailsUtil.setBondStateString
import com.example.bluetoothscannerapplication.utils.DeviceDetailsUtil.setDeviceTypeIcon
import com.example.bluetoothscannerapplication.utils.DeviceDetailsUtil.setDeviceTypeString

object Converter {
    @SuppressLint("MissingPermission") // checked in fragment
    fun convertBluetoothClassToDeviceDetails(device: BluetoothDevice): com.example.bluetoothscannerapplication.domain.entity.BluetoothDeviceEntity {
        Log.e("TAG", "setDeviceTypeIcon: ${device.bluetoothClass.majorDeviceClass} ", )
        return com.example.bluetoothscannerapplication.domain.entity.BluetoothDeviceEntity(
            setDeviceTypeIcon(device.bluetoothClass.majorDeviceClass),
            device.name,
            device.address,
            setBondStateString(device.bondState),
            setDeviceTypeString(device.bluetoothClass.majorDeviceClass)
        )
    }
}