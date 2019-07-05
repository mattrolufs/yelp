package com.rolufs.yelp.model.response


import com.google.gson.annotations.SerializedName

data class Open(
    val day: Int,
    val end: String,
    @SerializedName("is_overnight")
    val isOvernight: Boolean,
    val start: String
)