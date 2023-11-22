package com.example.bluetoothscannerapplication.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat

fun Context.checkBluetoothPermission(): Boolean {
    val bluetoothPermission =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.BLUETOOTH
        ) == PackageManager.PERMISSION_GRANTED
    val bluetoothAdminPermission =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.BLUETOOTH_ADMIN
        ) == PackageManager.PERMISSION_GRANTED

    return bluetoothPermission && bluetoothAdminPermission
}
