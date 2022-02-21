package uz.taxi.taxiapp.ui.taxi.rate

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import uz.taxi.taxiapp.TestOrder
import uz.taxi.taxiapp.data.OrderRate
import com.example.taxiapp.databinding.ViewPagerTaxiRateBinding
import uz.taxi.taxiapp.di.ResourceState
import uz.taxi.taxiapp.ui.MainViewModel
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
            viewModel.rateDataMore.value = item
            Log.d("item", viewModel.rateDataMore.value.toString())
            findNavController().navigate(R.id.action_taxiFragment_to_moreFragment)
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
        progressBar(true)
        dataObserve()
    }
    private fun dataObserve() {
        viewModel.apply {
            progressBar(false)
            orderRateAdded.observe(viewLifecycleOwner){
                progressBar(false)
                adapter.add(it)
            }
            orderRateModified.observe(viewLifecycleOwner){
                adapter.modified(it)
            }
            orderRateRemoved.observe(viewLifecycleOwner){
                adapter.removed(it)
            }
        }

    }

    private fun progressBar(b: Boolean) {
        binding.apply {
            if(b) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        }
    }

    private fun showMessage(message: String?) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

}