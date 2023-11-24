package com.example.bluetoothscannerapplication.core

import android.content.Context
import android.content.SharedPreferences

class SharedPreferenceManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        "AppSettings",
        Context.MODE_PRIVATE
    )

    fun saveSettings(){

    }

    fun loadSettings(){

    }
}