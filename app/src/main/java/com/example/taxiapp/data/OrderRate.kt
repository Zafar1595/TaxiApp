package com.example.taxiapp.data

import android.location.Location

data class OrderRate(
    val id: String,
    val adress: String,
    val phone: String,
    val coment: String? = null,
    val distance: String? = null,
    val location: Location? = null
)