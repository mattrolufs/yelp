package com.rolufs.yelp.model.response


import com.google.gson.annotations.SerializedName

data class Category(
    val alias: String,
    val title: String
)