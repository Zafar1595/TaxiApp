package com.example.taxiapp.ui.taxi.rate

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.data.OrderRate
import com.example.taxiapp.databinding.ViewPagerTaxiRateBinding
import com.example.taxiapp.di.ResourceState
import com.example.taxiapp.ui.MainViewModel
import org.koin.android.ext.android.inject

class ViewPagerRateFragment : Fragment(R.layout.view_pager_taxi_rate) {

    private var adapter: RateAdapter = RateAdapter()
    private lateinit var binding: ViewPagerTaxiRateBinding
    private val viewModel: MainViewModel by inject()

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


        binding.recyclerView.adapter = adapter

        adapter.setOnClickListener { item ->
            //item Click
        }

        adapter.setOnPhoneClickListener { phone ->
            // phone click
            // need open phone
            val phoneNumber = "+998$phone"
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + Uri.encode(phoneNumber)))
            requireActivity().startActivity(intent)
        }

        viewModel.orderRateDataObserve()
        dataObserve()
    }
    private fun dataObserve() {
        viewModel.orderRate.observe(viewLifecycleOwner){
            when(it.status){
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
                ResourceState.LOADING -> {
                    progressBar(true)
                }
                ResourceState.SUCCESS -> {
                    adapter.items = it.data as MutableList<OrderRate>
                }
            }
        }
    }

    private fun progressBar(b: Boolean) {

    }

    private fun showMessage(message: String?) {

    }

}