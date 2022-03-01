package uz.taxi.taxiapp.settings

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri

class Settings(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("TaxiSharedPref", Context.MODE_PRIVATE)

    var endTime: String
        set(value) = preferences.edit().putString("endTime", value).apply()
        get() = preferences.getString("endTime", "") ?: ""


    fun dialPhone(phoneNumber: String, activity: Activity){
        val phone = "+998$phoneNumber"
        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phone)))
        activity.startActivity(intent)
    }
}