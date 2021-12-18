package com.example.sabbir.flixtime.utils

import com.example.sabbir.flixtime.models.DateTime
import com.example.sabbir.flixtime.models.Departure
import com.example.sabbir.flixtime.models.TimeTable
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Created by himelhimu on 12/18/2021
 * @author Md Sabbir Ahmed
 * This is a free of charge whatever you want to do with it code,
 * However the individual libraries used in here might have their own licensing.
 */


/**
 * @author using custom deserializer, as the response json contains a lot of unnecessary fields that is not relevant to this task,
 * otherwise it should be done as a generated whole json classes and let gson take care it automatically by annotating @Serializednames in the member variables
 * of the model classes.
 * */

class DepartureJSONDeserilizer : JsonDeserializer<TimeTable> {

    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): TimeTable {



        val time = json!!.asJsonObject
        val dprt = time["timetable"].asJsonObject.getAsJsonArray("departures")

        val list = mutableListOf<Departure>()
        if (!dprt.isEmpty)
            for (obj in dprt!!) {
            val jsonObj = obj.asJsonObject
            val jsonObjDate = jsonObj!!["datetime"].asJsonObject
            val d = Departure(
                jsonObj["line_code"].asString, jsonObj["direction"].asString,
                DateTime(jsonObjDate["tz"].asString, jsonObjDate["timestamp"].asLong)
            )
            list.add(d)
        }
        return TimeTable(list)
    }


}