package com.example.bluetoothscannerapplication.presentation.dashboard

import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.content.IntentFilter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.bluetoothscannerapplication.BluetoothService
import com.example.bluetoothscannerapplication.R
import com.example.bluetoothscannerapplication.databinding.FragmentDashboardBinding
import com.example.bluetoothscannerapplication.extensions.makeShortToast
import com.example.bluetoothscannerapplication.receivers.BluetoothStatusReceiver


class DashboardFragment : Fragment() {
    private var _binding: FragmentDashboardBinding? = null
    private val binding get() = _binding!!

    lateinit var bluetoothService: BluetoothService
    lateinit var bluetoothStatusReceiver: BluetoothStatusReceiver
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bluetoothService = BluetoothService(requireContext())

        arguments?.let {
        }
    }

    private val bluetoothEnableRequest =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK) {
                binding.switchBluetooth.isChecked = true
            } else {
                binding.switchBluetooth.isChecked = false
                requireContext().makeShortToast("Please turn on the bluetooth.")
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bluetoothStatusReceiver = BluetoothStatusReceiver {
            binding.switchBluetooth.isChecked = it
        }

        binding.switchBluetooth.setOnClickListener {
            if (!bluetoothService.isBluetoothEnabled()) {
                bluetoothEnableRequest.launch(bluetoothService.enableBluetooth())
            } else {
                bluetoothService.disableBluetooth()
            }
        }

        // Set on click listeners
        binding.cardScanBluetooth.setOnClickListener {
            if (bluetoothService.isBluetoothEnabled()) {
                findNavController().navigate(R.id.action_dashboardFragment_to_deviceScanFragment)
            } else {
                requireContext().makeShortToast("Please turn on bluetooth.")
            }
        }

        binding.cardPairedDevices.setOnClickListener {
            if (bluetoothService.isBluetoothEnabled()) {
                findNavController().navigate(R.id.action_dashboardFragment_to_pairedDevicesFragment)
            } else {
                requireContext().makeShortToast("Please turn on bluetooth.")
            }
        }
        binding.cardSettings.setOnClickListener {
            findNavController().navigate(R.id.action_dashboardFragment_to_settingsFragment)
        }
    }

    override fun onResume() {
        super.onResume()
        val filter = IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED)
        requireContext().registerReceiver(bluetoothStatusReceiver, filter)
    }

    override fun onPause() {
        super.onPause()
        requireContext().unregisterReceiver(bluetoothStatusReceiver)
    }


}