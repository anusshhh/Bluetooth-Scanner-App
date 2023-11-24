package com.example.bluetoothscannerapplication.extensions

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.content.ContextCompat

fun Context.makeShortToast(message: String) {
    Toast.makeText(
        this, message, Toast.LENGTH_SHORT
    ).show()
}

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


fun Context.checkBluetoothScanPermission(): Boolean {
    return ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.BLUETOOTH_SCAN
    ) == PackageManager.PERMISSION_GRANTED
}


fun Context.checkLocationPermission(): Boolean {
    val fineLocationPermission =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    val coarseLocationPermission =
        ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

    return fineLocationPermission || coarseLocationPermission

}

fun Context.locationStatusCheck(): Boolean {
    val manager =
        this.getSystemService(Context.LOCATION_SERVICE) as LocationManager?
    return (manager!!.isProviderEnabled(LocationManager.GPS_PROVIDER))
}



