package com.rolufs.yelp.model.response.business

import org.junit.Test

import org.junit.Assert.*
import org.mockito.Mockito

class LocationTest {

    @Test
    fun formatAddress() {
        val mockLocation : Location = Mockito.mock(Location::class.java)
        val listAddress = listOf("Address Line 1", "Address Line 2")
        assertEquals( "Address Line 1\nAddress Line 2", mockLocation.formatAddress(listAddress))
    }
}