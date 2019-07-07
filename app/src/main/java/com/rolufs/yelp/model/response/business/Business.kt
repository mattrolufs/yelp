package com.rolufs.yelp.model.response.business


import com.google.gson.annotations.SerializedName

data class Business(
    val alias: String,
    val categories: List<Category>,
    val coordinates: Coordinates,
    @SerializedName("display_phone")
    val displayPhone: String,
    val hours: List<Hour>,
    val id: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("is_claimed")
    val isClaimed: Boolean,
    @SerializedName("is_closed")
    val isClosed: Boolean,
    val location: Location,
    val messaging: Messaging,
    val name: String,
    val phone: String,
    val photos: List<String>,
    val rating: Double,
    @SerializedName("review_count")
    val reviewCount: Int,
    val transactions: List<Any>,
    val url: String
)