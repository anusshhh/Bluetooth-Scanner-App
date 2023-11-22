package com.example.bluetoothscannerapplication.presentation.dashboard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import com.example.bluetoothscannerapplication.R
import com.example.bluetoothscannerapplication.presentation.paireddevice.PairedDevicesFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
}