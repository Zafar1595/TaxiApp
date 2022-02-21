package uz.taxi.taxiapp.data

data class IdentificationNumber(
    val id: String = "",
    var deviceId: String? = null,
    val identificationNumber: String = "",
    val taxiDriverId: String = "",
    var startTime: String? = null
)