package uz.taxi.taxiapp.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.taxi.taxiapp.data.OrderPath
import uz.taxi.taxiapp.data.OrderRate
import uz.taxi.taxiapp.di.Resource
import uz.taxi.taxiapp.firebase.FirebaseManager

class MainViewModel(private val db: FirebaseManager) : ViewModel() {

    val orderRate: MutableLiveData<Resource<List<OrderRate>>> = MutableLiveData()
    val orderPath: MutableLiveData<Resource<List<OrderPath>>> = MutableLiveData()


    val orderRateAdded: MutableLiveData<OrderRate> = MutableLiveData()
    val orderRateModified: MutableLiveData<OrderRate> = MutableLiveData()
    val orderRateRemoved: MutableLiveData<OrderRate> = MutableLiveData()

    fun orderRateDataObserve() {
        Log.d("TAG", "viewModel rate")

        db.observeOrderRate(
            {
                orderRateAdded.value = it
                Log.d("TAG", it.toString())
            }, {
                orderRateModified.value = it

            }, {
                orderRateRemoved.value = it
            }
        )
    }
    val orderPathAdded: MutableLiveData<OrderPath> = MutableLiveData()
    val orderPathModified: MutableLiveData<OrderPath> = MutableLiveData()
    val orderPathRemoved: MutableLiveData<OrderPath> = MutableLiveData()
    fun orderPathDataObserve() {
        db.observeOrderPath(
            {
                orderPathAdded.value = it
                Log.d("TAG", it.toString())
            }, {
                orderPathModified.value = it

            }, {
                orderPathRemoved.value = it
            }
        )
    }



    val sendOrderRateResponse: MutableLiveData<OrderRate> = MutableLiveData()
    fun sendOrderRateResponse() {
        // Надо найти обект по id
        //Если заказ принят то ответ надо записать в sendOrderRateResponse
        sendOrderRateResponse.value =
            OrderRate("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540", null, true)
    }


    val sendOrderPath: MutableLiveData<Resource<String>> = MutableLiveData()
    fun sendOrderPath(orderPath: OrderPath) {
        sendOrderPath.value = Resource.loading()
        db.sendOrderPath(orderPath, {
            sendOrderPath.value = Resource.success("success")
        }, {
            sendOrderPath.value = Resource.error(it)
        })
    }

    val sendOrderRate: MutableLiveData<Resource<String>> = MutableLiveData()
    fun sendOrderRate(orderRate: OrderRate) {
        sendOrderRate.value = Resource.loading()
        db.sendOrderRate(orderRate, {
            sendOrderRate.value = Resource.success("success")
        }, {
            sendOrderRate.value = Resource.error(it)
        })
    }


    val rateDataMore: MutableLiveData<OrderRate> = MutableLiveData()

}