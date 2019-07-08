package com.rolufs.yelp.model.response.review


import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat

data class Review(
    val id: String,
    val rating: Int,
    val text: String,
    @SerializedName("time_created")
    val timeCreated: String,
    val url: String,
    val user: User
){
    fun formatDate() : String {

        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var dateObj = sdf.parse(timeCreated)

        var date = SimpleDateFormat("MMM d, yyyy K:mm aa").format(dateObj);

        return date.toString()
    }
}