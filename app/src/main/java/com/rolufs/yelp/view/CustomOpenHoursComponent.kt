package com.rolufs.yelp.view

import android.content.Context
import android.icu.util.Calendar
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.rolufs.yelp.R
import com.rolufs.yelp.model.response.business.Hour
import com.rolufs.yelp.model.response.business.Open
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class CustomOpenHoursComponent @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr){

    var hour : Hour? = null
    var isOpenNow = false
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
        isOpenNow = hour!!.isOpenNow
        renderHours()
    }

    fun renderHours(){
        val sdf = SimpleDateFormat("d")
        val currentDate = sdf.format(Date())
        var currentDay = currentDate.toInt()

        (viewOfLayout as LinearLayout).removeAllViews()
        val opendays = this.hour!!.open.sortedBy { it.day }
        var j = 0;

        for(i in 0..6){
            val day = opendays.get(i - j)
            var hours = ""

            isOpenNow = true

            if (i != day.day) {
                j++
                hours = "Closed"
            } else {
                hours = day.formatHours()
                hours += if (isOpenNow && day.day == currentDay - 1) "  Open now" else ""
            }

            (viewOfLayout as LinearLayout).addView(
                CustomHoursItemComponent(Open.formatDay(i), hours, context, null, 0
                )
            )
        }
    }


}