package com.rolufs.yelp.model.response.business


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

open class Open(
    val day: Int,
    val end: String,
    @SerializedName("is_overnight")
    val isOvernight: Boolean,
    val start: String
) {

    companion object {

        fun formatDay(day: Int): String {

            var format: String = ""
            format = when (day) {
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
    }

    fun formatHours(start: String, end: String): String {

        val sdf = SimpleDateFormat("HHmm")
        var startObj = sdf.parse(start)
        var endObj = sdf.parse(end)

        var startTime = SimpleDateFormat("K:mm aa").format(startObj);
        var endTime = SimpleDateFormat("K:mm aa").format(endObj);

        return startTime.toString() + " - " + endTime.toString()
    }
}