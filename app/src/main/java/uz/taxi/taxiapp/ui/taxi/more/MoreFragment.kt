package uz.taxi.taxiapp.ui.taxi.more

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.taxiapp.R
import com.example.taxiapp.databinding.FragmentMoreBinding
import uz.taxi.taxiapp.di.ResourceState
import uz.taxi.taxiapp.ui.MainViewModel
import org.koin.android.ext.android.inject

class MoreFragment : Fragment(R.layout.fragment_more) {


    private lateinit var binding: FragmentMoreBinding

    private val viewModel: MainViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val item = viewModel.rateDataMore.value

        Log.d("TAG", viewModel.rateDataMore.value.toString())
        if(item != null){
            binding.apply {
                tvAdress.text = "Адрес: ${item?.adress}"
                tvPhone.text = "Телефон: ${item?.clientPhone}"
                tvDistance.text = "Дистанция: ${item?.distance}"
                tvComment.text = "Коментария: ${item?.comment}"
                buttonAccept.setOnClickListener {
                    item.status = true
//                viewModel.acceptOrderRate(item!!)
                    observeAcceptOrder()
                }

                buttonStart.setOnClickListener {
                    //start observe location
                }
                buttonStart.setOnClickListener {
                    // show result (distance and sum)
                }
            }
        }
    }

    private fun observeAcceptOrder() {
//        viewModel.acceptOrderRateResult.observe(viewLifecycleOwner) {
//            when (it.status) {
//                ResourceState.ERROR -> {
//                    showMessage(it.message)
//                    progressBar(false)
//                }
//                ResourceState.LOADING -> {
//                    progressBar(true)
//                }
//                ResourceState.SUCCESS -> {
//                    progressBar(false)
//                    startOrderProcess()
//                }
//            }
//        }
    }

    private fun startOrderProcess() {
        Toast.makeText(requireContext(), "cli", Toast.LENGTH_SHORT).show()
        binding.apply {
            llStartStop.visibility = View.VISIBLE
            buttonAccept.visibility = View.GONE
            llOrderProcess.visibility = View.VISIBLE
        }
    }

    private fun progressBar(b: Boolean) {

    }

    private fun showMessage(message: String?) {

    }


}