package com.example.sabbir.flixtime.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sabbir.flixtime.databinding.StationListItemBinding
import com.example.sabbir.flixtime.models.Departure

/**
 * Created by himelhimu on 12/18/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

class TimeTableAdapter(private val itemList:ArrayList<Departure>) : RecyclerView.Adapter<TimeTableViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeTableViewHolder {
        val binding = StationListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return TimeTableViewHolder.get(binding)
    }

    override fun onBindViewHolder(holder: TimeTableViewHolder, position: Int) {
        holder.bind(itemList[position])
    }

    fun clear(){
        itemList.clear()
        notifyDataSetChanged()
    }

    fun addAll(items:List<Departure>){
        itemList.addAll(items)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = itemList.size
}