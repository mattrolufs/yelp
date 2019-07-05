package com.rolufs.yelp.model.response


import com.google.gson.annotations.SerializedName

data class Messaging(
    val url: String,
    @SerializedName("use_case_text")
    val useCaseText: String
)