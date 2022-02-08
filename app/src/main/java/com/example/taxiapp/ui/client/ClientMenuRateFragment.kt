package com.example.taxiapp.ui.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.data.OrderRate
import com.example.taxiapp.databinding.FragmentClientMenuPathBinding
import com.example.taxiapp.databinding.FragmentClientMenuRateBinding
import com.example.taxiapp.di.ResourceState
import com.example.taxiapp.ui.MainViewModel
import org.koin.android.ext.android.inject

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentClientMenuRateBinding
    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientMenuRateBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonSend.setOnClickListener {
                val orderRate =
                    OrderRate(
                        System.currentTimeMillis().toString(),
                        etAdress.text.toString(),
                        etPhone.text.toString(),
                        etComent.text.toString(),
                    )
                viewModel.sendOrderRate(orderRate)
                viewModel.sendOrderRateResponse()
                observeStatus()
                llStatus.visibility = View.VISIBLE
            }
        }
        resultObserve()

    }

    private fun resultObserve() {
        viewModel.sendOrderRate.observe(viewLifecycleOwner) {
            when (it.status) {
                ResourceState.SUCCESS -> {
                    showMessage("Success")
                    progressBar(false)
                    viewModel.sendOrderPathResponse()
                    observeStatus()
                }
                ResourceState.LOADING -> {
                    progressBar(true)
                }
                ResourceState.ERROR -> {
                    showMessage(it.message!!)
                    progressBar(false)
                }
            }
        }
    }

    private fun observeStatus() {
        viewModel.sendOrderRateResponse.observe(viewLifecycleOwner){
            if(it.status == true){
                binding.ivStatusDefault.visibility = View.GONE
                binding.ivStatusTrue.visibility = View.VISIBLE
                binding.tvStatus.text = "Ваш заказ принят"
            }
        }
    }

    private fun progressBar(b: Boolean) {

    }

    private fun showMessage(s: String) {
        Toast.makeText(requireContext(), s, Toast.LENGTH_SHORT).show()
    }
}