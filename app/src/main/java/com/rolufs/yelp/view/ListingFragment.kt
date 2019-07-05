package com.rolufs.yelp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController

import com.rolufs.yelp.R
import com.rolufs.yelp.model.YelpApiService
import com.rolufs.yelp.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.listing_fragment.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

const val GETTY_ID = "zRlDhJgcwXEphTUhMaCfyw"

class ListingFragment : Fragment() {

    private lateinit var viewOfLayout: View
    private var reviews : TextView? = null

    companion object {
        fun newInstance() = ListingFragment()
    }

    private lateinit var viewModel: ListingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.listing_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)

        text_total_reviews.setOnClickListener{
            it.findNavController().navigate(R.id.action_listingFragment_to_reviewsFragment)
        }

        val yelpApiService = YelpApiService()

        GlobalScope.launch(Dispatchers.Main) {
            val business = yelpApiService.getBusiness(GETTY_ID).await()
            text_total_reviews.text = business.reviewCount.toString()
        }

    }

}
