package com.example.bluetoothscannerapplication.presentation.scan

import android.bluetooth.BluetoothDevice
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bluetoothscannerapplication.BluetoothService
import com.example.bluetoothscannerapplication.R
import com.example.bluetoothscannerapplication.extensions.checkBluetoothPermission
import com.example.bluetoothscannerapplication.extensions.checkLocationPermission
import com.example.bluetoothscannerapplication.extensions.locationStatusCheck
import com.example.bluetoothscannerapplication.extensions.makeShortToast
import com.example.bluetoothscannerapplication.receivers.BluetoothScanReceiver

class DeviceScanFragment : Fragment() {

    val receiver = BluetoothScanReceiver()
    lateinit var bluetoothService : BluetoothService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothService = BluetoothService(requireContext())
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_device_scan, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (requireContext().checkLocationPermission() && requireContext().locationStatusCheck()) {
            bluetoothService.startDiscovery()
        } else {
            requireContext().makeShortToast("Please turn on the location.")
        }
    }

    override fun onResume() {
        super.onResume()

        val filter = IntentFilter(BluetoothDevice.ACTION_FOUND)
        requireContext().registerReceiver(receiver, filter)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(receiver)
    }
}
