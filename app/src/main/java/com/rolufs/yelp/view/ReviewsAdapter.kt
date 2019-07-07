package com.rolufs.yelp.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.rolufs.yelp.R
import com.rolufs.yelp.model.response.review.Review
import kotlinx.android.synthetic.main.listing_fragment.view.*
import kotlinx.android.synthetic.main.review_item.view.*

class ReviewsAdapter(val reviews : MutableList<Review>) : RecyclerView.Adapter<ReviewsAdapter.ReviewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.review_item, parent, false)
        return ReviewHolder(view)
    }

    override fun getItemCount(): Int {
        return reviews.size
    }

    override fun onBindViewHolder(holder: ReviewHolder, position: Int) {
        val review = reviews.get(position)
        holder.setData(review)
    }


    inner class ReviewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        fun setData(review : Review?) {

            Glide
                .with(itemView.context)
                .load(review?.user!!.imageUrl)
                .fitCenter()
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .into(itemView.imageView);

            itemView.text_reviewer_name.text = review.user.name
            itemView.rating_bar.rating = review.rating.toFloat()
            //itemView.text_review_date = review.timeCreated
            itemView.text_review.text = review.text

        }

    }
}