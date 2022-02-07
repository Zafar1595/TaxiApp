package com.example.taxiapp.ui.taxi.path

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.databinding.ViewPagerTaxiPathBinding

class ViewPagerPathFragment: Fragment(R.layout.view_pager_taxi_path) {

    private lateinit var binding: ViewPagerTaxiPathBinding
    private val adapter = PathAdater()

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
        val list = listOf(
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540"),
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540"),
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540"),
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540"),
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540"),
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540"),
            OrderPath("dsafsdf","Zmsda","465465123","vxcvewv","6540")
        )

        adapter.items = list as MutableList<OrderPath>

        adapter.setOnClickListener { item ->
            //item Click
        }

        adapter.setOnPhoneClickListener { phone ->
            // phone click
            // need open phone
        }

    }
}