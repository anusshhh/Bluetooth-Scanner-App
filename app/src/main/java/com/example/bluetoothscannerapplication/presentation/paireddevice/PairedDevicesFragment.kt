package com.example.bluetoothscannerapplication.presentation.paireddevice

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetoothscannerapplication.databinding.FragmentPairedDevicesBinding
import com.example.bluetoothscannerapplication.extensions.checkBluetoothPermission
import com.example.bluetoothscannerapplication.utils.Converter

class PairedDevicesFragment : Fragment() {

    private var _binding: FragmentPairedDevicesBinding? = null
    private val binding get() = _binding!!
    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var pairedDevicesRecyclerView: RecyclerView
    private var pairedDevicesAdapter = PairedDevicesAdapter {
    }

    private val bluetoothPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.BLUETOOTH] == true &&
                permissions[Manifest.permission.BLUETOOTH_ADMIN] == true
            ) {
                // Bluetooth permissions granted, check if Bluetooth is enabled
            } else {
                // Handle the case where Bluetooth permissions are not granted
                // You may want to inform the user or request permissions again
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothManager =
            context?.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
        bluetoothAdapter = bluetoothManager.adapter
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPairedDevicesBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pairedDevicesRecyclerView = binding.rvPairedDevice
        pairedDevicesRecyclerView.adapter = pairedDevicesAdapter
        pairedDevicesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        if (requireContext().checkBluetoothPermission()) {
            val deviceList = mutableListOf<com.example.bluetoothscannerapplication.domain.entity.BluetoothDeviceEntity>()
            val pairedDevices = getPairedDevices()?: emptyList()
            if (pairedDevices.isNotEmpty()) {
                for (device in pairedDevices) {
                    deviceList.add(Converter.convertBluetoothClassToDeviceDetails(device))
                }
                pairedDevicesAdapter.submitList(deviceList)
            } else {
                // TODO
            }
        } else {
            requestPermission()
        }
    }

    private fun getPairedDevices(): List<BluetoothDevice>? {
        requireContext().checkBluetoothPermission()
        return bluetoothAdapter.bondedDevices.toList()
    }


    private fun checkBluetoothEnabled() {
        if (bluetoothAdapter == null || !bluetoothAdapter.isEnabled) {
            // Bluetooth is not enabled, request to enable Bluetooth
            // TODO REQUEST BLUETOOTH TO BE ENABLED
        } else {
            // Bluetooth is enabled, get the list of paired devices
            getPairedDevices()
        }
    }

    fun requestPermission() {
        bluetoothPermissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
        )

    }


}