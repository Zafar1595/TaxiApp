package uz.taxi.taxiapp.data

import android.location.Location
import com.google.firebase.firestore.GeoPoint

data class OrderRate(
    val id: String? = null,
    val adress: String? = null,
    val clientPhone: String? = null,
    val comment: String? = null,
    val distance: String? = null,
    val location: GeoPoint? = null,
    var status: Boolean? = false

)