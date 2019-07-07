package com.rolufs.yelp.model.response.business


import com.google.gson.annotations.SerializedName

data class Category(
    val alias: String,
    val title: String
)