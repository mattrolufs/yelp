package com.rolufs.yelp.model.response.review


import com.google.gson.annotations.SerializedName

data class User(
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    val name: String,
    @SerializedName("profile_url")
    val profileUrl: String
)