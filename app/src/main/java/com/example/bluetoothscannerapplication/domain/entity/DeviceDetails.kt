package com.example.bluetoothscannerapplication.domain.entity

import androidx.annotation.DrawableRes

data class DeviceDetails (
    @DrawableRes val icon : Int,
    val deviceName : String,
    val deviceAddress : String,
    val deviceBondState : String,
    val deviceType : String
)