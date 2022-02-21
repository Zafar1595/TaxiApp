package uz.taxi.taxiapp.data

import android.location.Location

data class Taxies(
    val carModel: String? = null,
    val carNumber: String? = null,
    val fullName: String = "",
    val id: String = "",
    val location: Location? = null,
    val orderCount: Int? = null,
    val orderId: MutableList<String>? = null,
    val phoneNumber: String? = null
)