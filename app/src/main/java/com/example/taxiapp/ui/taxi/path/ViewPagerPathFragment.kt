package com.example.taxiapp.ui.taxi.path

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.databinding.ViewPagerTaxiPathBinding
import com.example.taxiapp.di.ResourceState
import com.example.taxiapp.ui.MainViewModel
import org.koin.android.ext.android.inject

class ViewPagerPathFragment: Fragment(R.layout.view_pager_taxi_path) {

    private lateinit var binding: ViewPagerTaxiPathBinding
    private val adapter = PathAdater()
    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ViewPagerTaxiPathBinding.inflate(inflater, container, false)
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


        viewModel.orderPathDataObserve()
        dataObserve()
    }

    private fun dataObserve() {
        viewModel.orderPath.observe(viewLifecycleOwner){
            when(it.status){
                ResourceState.ERROR -> {
                    showMessage(it.message)
                }
                ResourceState.LOADING -> {
                    progressBar(true)
                }
                ResourceState.SUCCESS -> {
                    adapter.items = it.data as MutableList<OrderPath>
                }
            }
        }
    }

    private fun progressBar(b: Boolean) {

    }

    private fun showMessage(message: String?) {
    }
}