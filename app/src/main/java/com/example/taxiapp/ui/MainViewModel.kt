package com.example.taxiapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.data.OrderRate
import com.example.taxiapp.di.Resource

class MainViewModel : ViewModel() {

    val orderRate: MutableLiveData<Resource<List<OrderRate>>> = MutableLiveData()
    val orderPath: MutableLiveData<Resource<List<OrderPath>>> = MutableLiveData()

    fun orderRateDataObserve() {
        orderRate.value = Resource.loading()
        val list = listOf(
            OrderRate("dsadasd", "Kalenin 4/1", "998991234567", "cooesad", "56"),
            OrderRate("dsadasd", "SDAD 4/1", "998991234567", "cooesad", "3"),
            OrderRate("dsadasd", "KaleDSADnin 4/1", "998991234567", "cooesad", "2"),
            OrderRate("dsadasd", "Kvcxvxalenin 4/1", "998991234567", "cooesad", "1")
        )
        orderRate.value = Resource.success(list)


    }

    fun orderPathDataObserve() {
        orderPath.value = Resource.loading()
        val list = listOf(
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540"),
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540"),
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540"),
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540"),
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540"),
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540"),
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540")
        )
        orderPath.value = Resource.success(list)
    }

    val sendOrderRate: MutableLiveData<Resource<String>> = MutableLiveData()
    fun sendOrderRate(orderRate: OrderRate) {
        sendOrderRate.value = Resource.success("")
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
        sendOrderPath.value = Resource.success("")
    }

    val sendOrderPathResponse: MutableLiveData<OrderPath> = MutableLiveData()
    fun sendOrderPathResponse() {

        sendOrderPathResponse.value =
            OrderPath("dsafsdf", "Zmsda", "465465123", "vxcvewv", "6540", true)
        //Если заказ принят то ответ надо записать в sendOrderPathResponse
    }

    val rateDataMore: MutableLiveData<OrderRate> = MutableLiveData()


    val acceptOrderRateResult: MutableLiveData<Resource<Boolean>> = MutableLiveData()
    fun acceptOrderRate(item: OrderRate){

        //send item to firebase
        acceptOrderRateResult.value = Resource.loading()
        acceptOrderRateResult.value = Resource.success(true)
    }
}