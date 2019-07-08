package com.rolufs.yelp.view

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.rolufs.yelp.R
import com.rolufs.yelp.model.yelpId
import com.rolufs.yelp.viewmodel.ReviewsViewModel
import kotlinx.android.synthetic.main.reviews_fragment.*

class ReviewsFragment : Fragment() {

    companion object {
        fun newInstance() = ReviewsFragment()
    }

    private lateinit var viewModel: ReviewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.reviews_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ReviewsViewModel::class.java)

        val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        recycler_view_reviews.layoutManager = layoutManager

        viewModel.fetchReviews(yelpId).observe(this, Observer {reviews ->

            val reviewsAdapter = ReviewsAdapter(reviews.reviews)
            recycler_view_reviews.adapter = reviewsAdapter

        })


    }

}
