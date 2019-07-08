package com.rolufs.yelp.view

import android.content.Context
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rolufs.yelp.R
import com.rolufs.yelp.viewmodel.ReviewsViewModel
import kotlinx.android.synthetic.main.reviews_fragment.*

class ReviewsFragment : Fragment() {

    val args : ReviewsFragmentArgs by navArgs()

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

        if (isNetworkConnected()) {
            viewModel = ViewModelProviders.of(this).get(ReviewsViewModel::class.java)

            val layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            recycler_view_reviews.layoutManager = layoutManager

            viewModel.fetchReviews(args.businessId).observe(this, Observer {reviews ->

                val reviewsAdapter = ReviewsAdapter(reviews.reviews)
                recycler_view_reviews.adapter = reviewsAdapter

            })
        } else {
            AlertDialog.Builder(context!!).setTitle("No Internet Connection")
                .setMessage("Please check your internet connection and try again")
                .setPositiveButton(android.R.string.ok) { _, _ -> }
                .setIcon(android.R.drawable.ic_dialog_alert).show()
        }

    }

    private fun isNetworkConnected(): Boolean {
        val connectivityManager = context!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager //1
        val networkInfo = connectivityManager.activeNetworkInfo //2
        return networkInfo != null && networkInfo.isConnected //3
    }
}
