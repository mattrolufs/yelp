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
import com.rolufs.yelp.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.listing_fragment.*

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
        viewOfLayout = inflater.inflate(R.layout.listing_fragment, container, false)

        reviews = viewOfLayout.findViewById(R.id.text_total_reviews)

        reviews!!.setOnClickListener{ view ->
            view.findNavController().navigate(R.id.action_listingFragment_to_reviewsFragment)

        }

        return viewOfLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
