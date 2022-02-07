package com.example.taxiapp.data

data class OrderRate(
    val id: String,
    val adress: String,
    val phone: String,
    val coment: String? = null,
    val distance: String? = null
)