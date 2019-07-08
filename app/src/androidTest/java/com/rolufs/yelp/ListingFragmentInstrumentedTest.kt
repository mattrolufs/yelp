package com.rolufs.yelp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.rolufs.yelp.view.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ListingFragmentInstrumentedTest {

    @get:Rule
    val activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun shouldHaveStarRatingField(){
        onView(withResourceName("text_star_rating")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveTotalReviewsField(){
        onView(withResourceName("text_total_reviews")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveAddressField(){
        onView(withResourceName("text_address")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHavePhoneField(){
        onView(withResourceName("text_phone")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveWebsiteField(){
        onView(withResourceName("text_website")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveHoursField(){
        onView(withResourceName("text_hours")).check(matches(isDisplayed()))
    }

    @Test
    fun shouldHaveGettyCenterAddress(){
        onView(withId(R.id.text_address)).check(matches(withText("1200 Getty Center Dr \nLos Angeles, CA 90049")))
    }

    @Test
    fun shouldHaveNumberOfReviewsGreaterThan3000(){
        onView(withId(R.id.text_total_reviews)).check(matches(withSubstring("30")))
    }

    @Test
    fun shouldHavePhoneNumberEqualTo(){
        onView(withId(R.id.text_phone)).check(matches(withText("(310) 440-7300")))
    }

}