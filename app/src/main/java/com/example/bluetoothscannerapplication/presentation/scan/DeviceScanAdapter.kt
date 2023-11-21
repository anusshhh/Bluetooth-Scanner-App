package com.example.bluetoothscannerapplication.presentation.scan

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetoothscannerapplication.databinding.DevicesCardBinding
import com.example.bluetoothscannerapplication.domain.entity.DeviceDetails
import com.example.bluetoothscannerapplication.presentation.paireddevice.PairedDevicesAdapter
import kotlinx.coroutines.NonDisposableHandle.parent

class DeviceScanAdapter(private val onConnectButtonClick: (DeviceDetails) -> Unit) :
    RecyclerView.Adapter<DeviceScanAdapter.DeviceScanViewHolder>() {
    private var _binding: DevicesCardBinding? = null
    private val binding get() = _binding!!

    private var deviceList: MutableList<DeviceDetails> =
        emptyList<DeviceDetails>().toMutableList()


    inner class DeviceScanViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon = binding.ivDeviceIcon
        val deviceAddress = binding.tvDeviceAddress
        val deviceName = binding.tvDeviceName
        val connectButton = binding.btnConnect
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DeviceScanAdapter.DeviceScanViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = DevicesCardBinding.inflate(inflater, parent, false)
        return DeviceScanViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: DeviceScanViewHolder, position: Int) {
        holder.icon.setImageResource(deviceList[position].icon)
        holder.deviceName.text = deviceList[position].deviceName
        holder.deviceAddress.text = deviceList[position].deviceAddress

        holder.connectButton.setOnClickListener {
            onConnectButtonClick.invoke(deviceList[position])
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(deviceList: List<DeviceDetails>) {
        this.deviceList = deviceList.toMutableList()
        notifyDataSetChanged()
    }
}
