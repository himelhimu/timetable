package com.example.sabbir.flixtime.utils

import android.content.Context
import android.widget.Toast

/**
 * Created by himelhimu on 12/18/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */

fun Context.showShortToast(message:String){
    Toast.makeText(this,message,Toast.LENGTH_SHORT).show()
}