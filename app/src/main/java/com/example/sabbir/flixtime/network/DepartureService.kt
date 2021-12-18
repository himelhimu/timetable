package com.example.sabbir.flixtime.network

import com.example.sabbir.flixtime.models.TimeTable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by himelhimu on 12/17/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

interface DepartureService {

    @GET("mobile/v1/network/station/1/timetable.json")
    suspend fun getDepartureList():Response<TimeTable>
}