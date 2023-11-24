package com.example.bluetoothscannerapplication.presentation.paireddevice

import android.annotation.SuppressLint
import android.bluetooth.BluetoothDevice
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.bluetoothscannerapplication.databinding.DevicesCardBinding
import com.example.bluetoothscannerapplication.domain.entity.BluetoothDeviceEntity
import com.example.bluetoothscannerapplication.utils.Converter

class PairedDevicesAdapter(private val onUnpairButtonClick: (BluetoothDevice) -> Unit) :
    RecyclerView.Adapter<PairedDevicesAdapter.PairedDevicesViewHolder>() {
    private var _binding: DevicesCardBinding? = null
    private val binding get() = _binding!!

    private var deviceList: MutableList<BluetoothDeviceEntity> =
        emptyList<BluetoothDeviceEntity>().toMutableList()

    private var bluetoothDeviceList: MutableList<BluetoothDevice> =
        emptyList<BluetoothDevice>().toMutableList()


    inner class PairedDevicesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val icon = binding.ivDeviceIcon
        val deviceAddress = binding.tvDeviceAddress
        val deviceName = binding.tvDeviceName
        val connectButton = binding.btnConnect
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PairedDevicesAdapter.PairedDevicesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = DevicesCardBinding.inflate(inflater, parent, false)
        return PairedDevicesViewHolder(binding.root)
    }

    override fun onBindViewHolder(
        holder: PairedDevicesAdapter.PairedDevicesViewHolder,
        position: Int
    ) {
        holder.icon.setImageResource(deviceList[position].icon)
        holder.deviceName.text = deviceList[position].deviceName
        holder.deviceAddress.text = deviceList[position].deviceAddress

        holder.connectButton.setOnClickListener {
            onUnpairButtonClick.invoke(bluetoothDeviceList[position])
            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return deviceList.size
    }

    /*
        @SuppressLint("NotifyDataSetChanged")
        fun submitList(deviceList : List<BluetoothDeviceEntity>){
            this.deviceList=deviceList.toMutableList()
            notifyDataSetChanged()
        }
    */
    @SuppressLint("NotifyDataSetChanged")
    fun submitList(bluetoothDeviceList: List<BluetoothDevice>) {
        this.bluetoothDeviceList = bluetoothDeviceList.toMutableList()
        for (device in bluetoothDeviceList) {
            deviceList.add(Converter.convertBluetoothClassToDeviceDetails(device))
        }
        notifyDataSetChanged()
    }

    fun removeItem(pos: Int) {
        deviceList.removeAt(pos)
        notifyItemRemoved(pos)
    }

}