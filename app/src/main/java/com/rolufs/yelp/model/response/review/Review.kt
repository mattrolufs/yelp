package com.rolufs.yelp.model.response.review


import com.google.gson.annotations.SerializedName

data class Review(
    val id: String,
    val rating: Int,
    val text: String,
    @SerializedName("time_created")
    val timeCreated: String,
    val url: String,
    val user: User
)