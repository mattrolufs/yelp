package com.rolufs.yelp.model.response


import com.google.gson.annotations.SerializedName
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

data class Open(
    val day: Int,
    val end: String,
    @SerializedName("is_overnight")
    val isOvernight: Boolean,
    val start: String
){
    fun formatDay() : String {

        var format : String = ""
        format = when(day){
            0 -> "Mon"
            1 -> "Tue"
            2 -> "Wed"
            3 -> "Thu"
            4 -> "Fri"
            5 -> "Sat"
            6 -> "Sun"
            else -> ""
        }

        return format
    }

    fun formatHours() : String {

        var formatStart : String = ""
        var formatEnd : String = ""

        formatStart = start.substring(0, start.length - 2) + ":" + start.substring(start.length - 2, start.length)
        formatEnd = end.substring(0, end.length - 2) + ":" + end.substring(end.length - 2, end.length)

        val sdf = SimpleDateFormat("K:mm")
        var startObj = sdf.parse(formatStart)
        var endObj = sdf.parse(formatEnd)

        var startTime = SimpleDateFormat("K:mm aa").format(startObj);
        var endTime = SimpleDateFormat("K:mm aa").format(endObj);

        return startTime.toString() + " - " + endTime.toString()
    }
}