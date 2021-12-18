package com.example.sabbir.flixtime.ui.main

import androidx.recyclerview.widget.RecyclerView
import com.example.sabbir.flixtime.databinding.StationListItemBinding
import com.example.sabbir.flixtime.models.Departure

/**
 * Created by himelhimu on 12/18/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

class TimeTableViewHolder(private val binding: StationListItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    companion object {

        fun get(binding: StationListItemBinding): TimeTableViewHolder {
            return TimeTableViewHolder(binding)
        }
    }

    fun bind(item: Departure) {
        binding.departure = item
        binding.executePendingBindings()
    }
}