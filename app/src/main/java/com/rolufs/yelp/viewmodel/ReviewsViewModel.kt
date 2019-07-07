package com.rolufs.yelp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.rolufs.yelp.model.YelpApiService
import com.rolufs.yelp.model.response.review.Review
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ReviewsViewModel : ViewModel() {

    var mutableReviews = MutableLiveData<ArrayList<Review>>()
    var reviews : ArrayList<Review>? = null

    fun fetchReviews(businessId : String) : MutableLiveData<ArrayList<Review>>{

        GlobalScope.launch(Dispatchers.Main) {
            val yelpApiService = YelpApiService()
            reviews = yelpApiService.getReviews(businessId).await()
            mutableReviews.value = reviews
        }

        return mutableReviews
    }
}
