package com.example.taxiapp.data

data class OrderPath(
    val id: String,
    val adress: String,
    val phone: String,
    val coment: String? = null,
    val sum: String? = null,
    val status: Boolean? = false
)