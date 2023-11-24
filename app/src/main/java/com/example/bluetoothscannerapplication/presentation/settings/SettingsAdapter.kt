package com.example.bluetoothscannerapplication.presentation.settings

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.bluetoothscannerapplication.R
import com.example.bluetoothscannerapplication.databinding.SettingsCardBinding
import com.example.bluetoothscannerapplication.domain.entity.BluetoothSettingsEntity
import com.example.bluetoothscannerapplication.utils.Constants

class SettingsAdapter : RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder>() {

    private var _binding: SettingsCardBinding? = null
    private val binding get() = _binding!!

    private var bluetoothSettingsEntityLists = mutableListOf(
        BluetoothSettingsEntity(
            "Retry Count",
            "Number of connections attempt",
            R.drawable.ic_retry,
            Constants.retryCount,
            Constants.retryCount[0]
        ),
        BluetoothSettingsEntity(
            "Retry After",
            "Pause between profile connection attempts",
            R.drawable.ic_retry,
            Constants.retryAfter,
            Constants.retryCount[0]
        ),
        BluetoothSettingsEntity(
            "Device Timeout",
            "A timeout after which the following device is processed",
            R.drawable.ic_timer,
            Constants.deviceTimeout,
            Constants.deviceTimeout[0]
        ),
    )

    inner class SettingsViewHolder(itemView: View) : ViewHolder(itemView) {
        val icon = binding.ivSettingIcon
        val title = binding.tvTitle
        val subtitle = binding.tvSubtitle
        val spinner = binding.spinnerSettings

        init {
            binding.spinnerSettings.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        bluetoothSettingsEntityLists[adapterPosition].selectedOption =
                            bluetoothSettingsEntityLists[adapterPosition].options[p2]
                    }

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                }
        }


    }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): SettingsAdapter.SettingsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        _binding = SettingsCardBinding.inflate(inflater, parent, false)
        return SettingsViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SettingsAdapter.SettingsViewHolder, position: Int) {
        holder.icon.setImageResource(bluetoothSettingsEntityLists[position].icon)
        holder.title.text = bluetoothSettingsEntityLists[position].title
        holder.subtitle.text = bluetoothSettingsEntityLists[position].subTitle

        // TODO spinner implementation
        val arrayAdapter = ArrayAdapter(
            binding.root.context,
            androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
            bluetoothSettingsEntityLists[position].options
        )
        binding.spinnerSettings.adapter = arrayAdapter
        binding.spinnerSettings.setSelection(
            bluetoothSettingsEntityLists[position].options.indexOf(
                bluetoothSettingsEntityLists[position].selectedOption
            )
        )

    }

    override fun getItemCount(): Int {
        return bluetoothSettingsEntityLists.size
    }

    fun getItem(): List<BluetoothSettingsEntity> {
        return bluetoothSettingsEntityLists
    }

}