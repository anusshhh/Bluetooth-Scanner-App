package com.example.bluetoothscannerapplication.presentation.paireddevice

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetoothscannerapplication.BluetoothService
import com.example.bluetoothscannerapplication.databinding.FragmentPairedDevicesBinding
import com.example.bluetoothscannerapplication.extensions.checkBluetoothPermission

class PairedDevicesFragment : Fragment() {

    private var _binding: FragmentPairedDevicesBinding? = null
    private val binding get() = _binding!!
    lateinit var bluetoothManager: BluetoothManager
    lateinit var bluetoothAdapter: BluetoothAdapter
    lateinit var pairedDevicesRecyclerView: RecyclerView
    lateinit var bluetoothService : BluetoothService
    private val bluetoothPermissionLauncher: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            if (permissions[Manifest.permission.BLUETOOTH] == true &&
                permissions[Manifest.permission.BLUETOOTH_ADMIN] == true
            ) {
                // TODO Bluetooth permissions granted, check if Bluetooth is enabled
            } else {
                // TODO Handle the case where Bluetooth permissions are not granted
                // TODO You may want to inform the user or request permissions again
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
         bluetoothService = BluetoothService(requireContext())
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
        val pairedDevicesAdapter = PairedDevicesAdapter {
            bluetoothService.unpairDevice(it)

        }
        pairedDevicesRecyclerView.adapter = pairedDevicesAdapter
        pairedDevicesRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)


        if (requireContext().checkBluetoothPermission()) {
            val pairedDevices = getPairedDevices()
            if (pairedDevices.isNotEmpty()) {
                pairedDevicesAdapter.submitList(pairedDevices)
            } else {
                // TODO
            }
        } else {
            requestPermission()
        }
    }

    private fun getPairedDevices(): List<BluetoothDevice> {
        return bluetoothService.getAllPairedDevices().toList()
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