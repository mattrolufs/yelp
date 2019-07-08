package com.rolufs.yelp.view

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rolufs.yelp.R
import com.rolufs.yelp.model.yelpId
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
        return viewOfLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (isNetworkConnected()) {
            viewModel = activity?.run{ViewModelProviders.of(this).get(ListingViewModel::class.java)}?:
                    throw Exception("Invalid Activity")

            viewModel.fetchBusiness(yelpId).observe(this, Observer {

                Glide
                    .with(context!!)
                    .load(it?.imageUrl)
                    .fitCenter()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                    .into(image_header);

                card_reviews.setOnClickListener{view ->
                    val action = ListingFragmentDirections.actionListingFragmentToReviewsFragment(yelpId)
                    view.findNavController().navigate(action)
                }

                card_address.setOnClickListener{view ->
                    val gmmIntentUri = Uri.parse("geo:${it.coordinates.latitude},${it.coordinates.longitude}?q=${it.name}");
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }

                card_phone.setOnClickListener{view ->
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${it?.displayPhone?.toString()}")
                    startActivity(intent)
                }

                card_website.setOnClickListener{view ->
                    val uriUrl = Uri.parse(it?.url)
                    val browserIntent = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(browserIntent)
                }

                text_phone.text = it?.displayPhone?.toString()
                text_name.text = it?.name
                text_address.text = it?.location?.formatAddress(it.location.displayAddress)?.toString()
                text_total_reviews.text = "${it?.let { it.rating.toString() + " Rating\n" }}${it?.reviewCount.toString()} Reviews"
                rating_bar.rating = it?.rating?.toFloat()!!
                customOpenHoursComponent.setHours(it?.hours?.get(0))
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
