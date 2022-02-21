package uz.taxi.taxiapp.ui.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import uz.taxi.taxiapp.TestOrder
import uz.taxi.taxiapp.data.OrderPath
import com.example.taxiapp.databinding.FragmentClientMenuPathBinding
import uz.taxi.taxiapp.di.ResourceState
import uz.taxi.taxiapp.ui.MainViewModel
import org.koin.android.ext.android.inject


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
                TestOrder.orderPath.add(orderPath)
                viewModel.sendOrderPath(orderPath)
//                viewModel.sendOrderPathResponse()
                observeStatus()
                llStatus.visibility = View.VISIBLE
            }
        }
        resultObserve()
    }

    private fun observeStatus() {
//        viewModel.sendOrderPathResponse.observe(viewLifecycleOwner){
//            if(it.status == true){
//                binding.ivStatusDefault.visibility = View.GONE
//                binding.ivStatusTrue.visibility = View.VISIBLE
//                binding.tvStatus.text = "Ваш заказ принят"
//            }
//        }
    }

    private fun resultObserve() {
        viewModel.sendOrderPath.observe(viewLifecycleOwner){
            when(it.status){
                ResourceState.SUCCESS -> {
                    showMessage("Success")
                    progressBar(false)
//                    viewModel.sendOrderPathResponse()
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

    private fun progressBar(b: Boolean) {

    }

    private fun showMessage(s: String) {
        Toast.makeText(requireContext(), "Success", Toast.LENGTH_SHORT).show()
    }

}