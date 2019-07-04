package com.rolufs.yelp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.rolufs.yelp.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listing_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setSupportActionBar(toolbar);
    }
}
