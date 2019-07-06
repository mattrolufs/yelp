package com.rolufs.yelp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import com.rolufs.yelp.R
import com.rolufs.yelp.model.response.Hour
import kotlinx.android.synthetic.main.hours_item.view.*

class CustomHoursItemComponent @JvmOverloads constructor(
        context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
    ) : LinearLayout(context, attrs, defStyleAttr){

    var day : String = ""
    var hours : String = ""

    constructor(day: String, hour: String, context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr) {

//        this.day = day
//        this.hours = hour

        text_day.text = day
        text_hours_open.text = hour

    }

    init {
        LayoutInflater.from(context).inflate(R.layout.hours_item, this, true)
        orientation = HORIZONTAL

        if(day == "" && hours == "") {

            attrs?.let {

                val typedArray = context.obtainStyledAttributes(
                    it,
                    R.styleable.CustomHoursItemComponent, 0, 0
                )
                day = typedArray.getString(R.styleable.CustomHoursItemComponent_day) ?: ""
                hours = typedArray.getString(R.styleable.CustomHoursItemComponent_hours) ?: ""

                typedArray.recycle()
            }
        }



    }


}