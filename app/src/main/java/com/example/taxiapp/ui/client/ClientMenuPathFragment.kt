package com.example.taxiapp.ui.client

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.databinding.FragmentClientMenuPathBinding
import com.example.taxiapp.di.ResourceState
import com.example.taxiapp.ui.MainViewModel
import org.koin.android.ext.android.inject
import java.util.*


class ClientMenuPathFragment : Fragment() {

    private lateinit var binding: FragmentClientMenuPathBinding
    private val viewModel: MainViewModel by inject()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentClientMenuPathBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            buttonSend.setOnClickListener {
                val orderPath =
                    OrderPath(
                        System.currentTimeMillis().toString(),
                        etAdress.text.toString(),
                        etPhone.text.toString(),
                        etComent.text.toString(),
                        etSum.text.toString()
                    )
                viewModel.sendOrderPath(orderPath)
            }
        }
        resultObserve()
    }

    private fun resultObserve() {
        viewModel.sendOrderPath.observe(viewLifecycleOwner){
            when(it.status){
                ResourceState.SUCCESS -> {
                    showMessage("Success")
                    progressBar(false)
                    findNavController().popBackStack()
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

    private fun progressBar(b: Boolean) {

    }

    private fun showMessage(s: String) {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
    }

}