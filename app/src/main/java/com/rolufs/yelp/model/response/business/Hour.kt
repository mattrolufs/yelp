package com.rolufs.yelp.model.response.business


import com.google.gson.annotations.SerializedName

data class Hour(
    val `open`: List<Open>,
    @SerializedName("hours_type")
    val hoursType: String,
    @SerializedName("is_open_now")
    val isOpenNow: Boolean
)