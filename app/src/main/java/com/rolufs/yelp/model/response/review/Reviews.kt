package com.rolufs.yelp.model.response.review


import com.google.gson.annotations.SerializedName

data class Reviews(
    @SerializedName("possible_languages")
    val possibleLanguages: List<String>,
    val reviews: List<Review>,
    val total: Int
)