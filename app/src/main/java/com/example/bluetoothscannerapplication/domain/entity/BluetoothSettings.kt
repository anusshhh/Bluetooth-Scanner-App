package com.example.bluetoothscannerapplication.domain.entity

import androidx.annotation.DrawableRes

data class BluetoothSettings(
    val title: String,
    val subTitle: String,
    @DrawableRes val icon: Int,
    val options: List<Int>,
    val selectedOption: Int
)