package com.rolufs.yelp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel;
import com.rolufs.yelp.model.YelpApiService
import com.rolufs.yelp.model.response.business.Business
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListingViewModel : ViewModel() {
    var mutableBusiness = MutableLiveData<Business>()
    var business : Business? = null

    fun fetchBusiness(businessId : String): MutableLiveData<Business>{

        GlobalScope.launch(Dispatchers.Main){
            val yelpApiService = YelpApiService()
            business = yelpApiService.getBusiness(businessId).await()
            mutableBusiness.value = business
        }

        return mutableBusiness
    }

}
