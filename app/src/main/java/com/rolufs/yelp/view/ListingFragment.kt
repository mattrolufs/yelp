package com.rolufs.yelp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

import com.rolufs.yelp.R
import com.rolufs.yelp.model.yelpId
import com.rolufs.yelp.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.listing_fragment.*

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

        viewModel.fetchBusiness(yelpId).observe(this, Observer {
            text_address.text = it?.location?.formatAddress()?.toString()
            text_phone.text = it?.displayPhone?.toString()
            text_website.text = it?.url
            text_name.text = it?.name
            text_hours.text = it?.hours.toString()
            text_total_reviews.text = it?.reviewCount.toString()
            rating_bar.rating = it?.rating?.toFloat()!!
        })
    }

}
