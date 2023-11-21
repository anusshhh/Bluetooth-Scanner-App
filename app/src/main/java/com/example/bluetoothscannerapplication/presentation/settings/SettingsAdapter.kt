package com.example.bluetoothscannerapplication.presentation.settings

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bluetoothscannerapplication.R
import com.example.bluetoothscannerapplication.databinding.SettingsCardBinding
import com.example.bluetoothscannerapplication.domain.entity.BluetoothSettings
import com.example.bluetoothscannerapplication.utils.Constants

class SettingsAdapter:
    RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    private var _binding: SettingsCardBinding? = null
    private val binding get() = _binding!!

    private var bluetoothSettingsList = mutableListOf<BluetoothSettings>(
        BluetoothSettings("Retry Count", "Number of connections attempt",R.drawable.ic_retry,Constants.retryCount,Constants.retryCount[0]),
        BluetoothSettings("Retry After", "Pause between profile connection attempts",R.drawable.ic_retry,Constants.retryAfter,Constants.retryCount[0]),
        BluetoothSettings("Retry Count", "A timeout after which the following device is processed",R.drawable.ic_timer,Constants.deviceTimeout,Constants.deviceTimeout[0]),
    )

    inner class SettingsViewHolder(itemView : View) : ViewHolder(itemView) {
        val icon = binding.ivSettingIcon
        val title = binding.tvTitle
        val subtitle = binding.tvSubtitle
        val spinner = binding.spinnerSettings
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SettingsAdapter.SettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = SettingsCardBinding.inflate(inflater, parent, false)
        return SettingsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SettingsAdapter.SettingsViewHolder, position: Int) {
        holder.icon.setImageResource(bluetoothSettingsList[position].icon)
        holder.title.text=bluetoothSettingsList[position].title
        holder.subtitle.text=bluetoothSettingsList[position].subTitle

        // TODO spinner implementation

    }

    override fun getItemCount(): Int {
        return bluetoothSettingsList.size
    }

}