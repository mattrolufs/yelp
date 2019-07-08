package com.rolufs.yelp.view

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.rolufs.yelp.R
import com.rolufs.yelp.model.response.business.Business
import com.rolufs.yelp.viewmodel.ListingViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.listing_fragment.*
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController
    private lateinit var viewModel: ListingViewModel
    private lateinit var business: Business

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_nav);

        navController = Navigation.findNavController(this, R.id.nav_host_fragment)

        //NavigationUI.setupActionBarWithNavController(this, navController)

        toolbar_nav?.let {
            NavigationUI.setupWithNavController(toolbar_nav, navController)
        }

        viewModel = ViewModelProviders.of(this).get(ListingViewModel::class.java)

        viewModel.mutableBusiness.observe(this, Observer<Business> { business ->
            this.business = business
        })

        menu_bottom.setOnNavigationItemSelectedListener (BottomNavigationView.OnNavigationItemSelectedListener {

            when(it!!.itemId){
                R.id.menu_map -> {
                    val gmmIntentUri = Uri.parse("geo:${business.coordinates.latitude},${business.coordinates.longitude}?q=${business.name}");
                    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                    return@OnNavigationItemSelectedListener false
                }
                R.id.menu_web -> {
                    val uriUrl = Uri.parse(business?.url)
                    val browserIntent = Intent(Intent.ACTION_VIEW, uriUrl)
                    startActivity(browserIntent)
                    return@OnNavigationItemSelectedListener false
                }
                R.id.menu_phone -> {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${business?.displayPhone?.toString()}")
                    startActivity(intent)
                    return@OnNavigationItemSelectedListener false
                }

            }
            return@OnNavigationItemSelectedListener false

        })

    }

    override fun onResume() {
        super.onResume()


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navController,null)
    }

}
