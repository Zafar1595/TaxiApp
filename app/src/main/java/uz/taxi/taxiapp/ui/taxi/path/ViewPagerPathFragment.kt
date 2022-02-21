package uz.taxi.taxiapp.ui.taxi.path

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import com.example.taxiapp.databinding.ViewPagerTaxiPathBinding
import uz.taxi.taxiapp.ui.MainViewModel
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

        viewModel.orderPathDataObserve()
        progressBar(true)
        dataObserve()
    }

    private fun dataObserve() {
        viewModel.apply {
            progressBar(false)
            orderPathAdded.observe(viewLifecycleOwner){
                adapter.add(it)
            }
            orderPathModified.observe(viewLifecycleOwner){
                adapter.modified(it)
            }
            orderPathRemoved.observe(viewLifecycleOwner){
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
    }
}