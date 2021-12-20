package com.example.sabbir.flixtime.utils

import android.content.Context
import android.widget.Toast
import com.example.sabbir.flixtime.models.DateTime
import java.text.SimpleDateFormat
import java.util.*
import androidx.core.content.ContextCompat.getSystemService

import android.net.ConnectivityManager
import androidx.core.content.ContextCompat


/**
 * Created by himelhimu on 12/18/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

fun Context.showShortToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}


fun convertToHumanReadableDateTime(dateTime: DateTime): String {

    try {
        val calendar: Calendar = Calendar.getInstance()
        val tz: TimeZone = TimeZone.getTimeZone(dateTime.timeZone)
        calendar.timeInMillis = dateTime.timeStamp * 1000
        calendar.add(Calendar.MILLISECOND, tz.getOffset(calendar.timeInMillis))
        val sdf = SimpleDateFormat("HH:mm")
        val currenTimeZone: Date = calendar.time as Date
        return sdf.format(currenTimeZone)
    } catch (e: Exception) {
    }
    return "n/a"
}