package com.rolufs.yelp.view

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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
        viewOfLayout = inflater.inflate(R.layout.listing_fragment, container, false)
        return viewOfLayout
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = activity?.run{ViewModelProviders.of(this).get(ListingViewModel::class.java)}?:
                throw Exception("Invalid Activity")

//        text_total_reviews.setOnClickListener{
//            it.findNavController().navigate(R.id.action_listingFragment_to_reviewsFragment)
//        }

        viewModel.fetchBusiness(yelpId).observe(this, Observer {

            Glide
                .with(context!!)
                .load(it?.imageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(image_header);

            text_address.text = it?.location?.formatAddress()?.toString()

            card_reviews.setOnClickListener{view ->
                //view.findNavController().navigate(R.id.action_listingFragment_to_reviewsFragment)
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
//            text_website.movementMethod = LinkMovementMethod.getInstance()
//            val website = it?.url
//            text_website.text = Html.fromHtml("<a href=\"${website}\">Visit Website</a>")
            text_name.text = it?.name

//            var compHours = CustomHoursItemComponent(this.context!!, null)
//            compHours.day = "mon"
//            compHours.hours = "1-2"


            //var compOpen = CustomOpenHoursComponent(it?.hours?.get(0), this.context!!, null, 0)
            customOpenHoursComponent.setHours(it?.hours?.get(0))

            //(viewOfLayout as ConstraintLayout).addView(compOpen)

            //text_hours.text = it?.hours.toString()
            text_total_reviews.text = "of ${it?.reviewCount.toString()} Reviews"
            rating_bar.rating = it?.rating?.toFloat()!!

        })


    }



}
