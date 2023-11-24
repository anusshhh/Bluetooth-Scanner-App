package com.example.bluetoothscannerapplication.domain.entity

import androidx.annotation.DrawableRes

data class BluetoothSettingsEntity(
    val title: String,
    val subTitle: String,
    @DrawableRes val icon: Int,
    val options: List<Int>,
    var selectedOption: Int
)