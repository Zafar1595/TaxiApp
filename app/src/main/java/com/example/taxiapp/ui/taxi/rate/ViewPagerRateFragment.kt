package com.example.taxiapp.ui.taxi.rate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderRate
import com.example.taxiapp.databinding.ViewPagerTaxiRateBinding

class ViewPagerRateFragment : Fragment(R.layout.view_pager_taxi_rate) {

    private var adapter: RateAdapter = RateAdapter()
    private lateinit var binding: ViewPagerTaxiRateBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewPagerTaxiRateBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = listOf(
            OrderRate("dsadasd", "Kalenin 4/1", "998991234567", "cooesad", "56"),
            OrderRate("dsadasd", "SDAD 4/1", "998991234567", "cooesad", "3"),
            OrderRate("dsadasd", "KaleDSADnin 4/1", "998991234567", "cooesad", "2"),
            OrderRate("dsadasd", "Kvcxvxalenin 4/1", "998991234567", "cooesad", "1")
        )
        binding.recyclerView.adapter = adapter
        adapter.items = list as MutableList<OrderRate>

        adapter.setOnClickListener { item ->
            //item Click
        }

        adapter.setOnPhoneClickListener { phone ->
            // phone click
            // need open phone
        }

    }

}