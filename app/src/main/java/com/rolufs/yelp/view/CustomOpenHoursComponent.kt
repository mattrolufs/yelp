package com.rolufs.yelp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.rolufs.yelp.R
import com.rolufs.yelp.model.response.Hour
import com.rolufs.yelp.model.response.Open
import kotlinx.android.synthetic.main.hours_item.view.*

class CustomOpenHoursComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    var hour : Hour? = null
    private lateinit var viewOfLayout: View

    constructor(hour: Hour?, context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr) {
        this.hour = hour
    }

    init {
        viewOfLayout = LayoutInflater.from(context).inflate(R.layout.open_hours_container, this, true)
        orientation = VERTICAL
    }

    fun setHours(hour : Hour?){
        this.hour = hour
        renderHours()
    }

    fun renderHours(){
        for(open in this.hour!!.open){
            (viewOfLayout as LinearLayout).addView(CustomHoursItemComponent(open.formatDay(), open.formatHours(), context, null, 0))
        }
    }


}