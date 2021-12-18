package com.example.sabbir.flixtime.models

import com.google.gson.annotations.SerializedName

/**
 * Created by himelhimu on 12/17/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

data class TimeTable(
    val departureList:List<Departure>)