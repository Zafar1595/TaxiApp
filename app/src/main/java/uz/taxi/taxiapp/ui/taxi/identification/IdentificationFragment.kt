package uz.taxi.taxiapp.ui.taxi.identification

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import com.example.taxiapp.databinding.FragmentIdentificationBinding
import org.koin.android.ext.android.inject
import uz.taxi.taxiapp.di.ResourceState
import uz.taxi.taxiapp.ui.MainViewModel

class IdentificationFragment : Fragment() {

    private lateinit var binding: FragmentIdentificationBinding
    private val viewModel: MainViewModel by inject()
    private var deviceId: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIdentificationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            editTextID.setText("45648156")
            buttonOK.setOnClickListener {
                if(!editTextID.text.isNullOrEmpty()){
                    checkDeviceId()
                }else{
                    showMessage("ID номеринизди киритин.")
                }
            }
        }

        observeResult()

    }

    private fun checkDeviceId() {
        val shp: SharedPreferences = requireContext().getSharedPreferences("MySharedPref",
            AppCompatActivity.MODE_PRIVATE
        )
        deviceId = shp.getString("deviceId", "")
        Log.d("deviceId", "$deviceId")
        if (deviceId.isNullOrEmpty()) {
            saveDeviceId()
        }else{
            viewModel.checkID(binding.editTextID.text.toString(), deviceId!!)
        }

    }
    private fun saveDeviceId() {
        val sharedPreferences: SharedPreferences =
            requireContext().getSharedPreferences("MySharedPref", AppCompatActivity.MODE_PRIVATE)
        val myEditor: SharedPreferences.Editor = sharedPreferences.edit()
        myEditor.putString("deviceId", System.currentTimeMillis().toString())
        myEditor.apply()
        checkDeviceId()
    }

    private fun observeResult() {
        viewModel.checkIdResult.observe(viewLifecycleOwner){
            when(it.status){
                ResourceState.LOADING -> {
                    progressBar(true)
                }
                ResourceState.ERROR -> {
                    progressBar(false)
                    showMessage(it.message.toString())
                }
                ResourceState.SUCCESS -> {
                    progressBar(false)
                    updateUI()
                }
            }
        }
    }

    private fun progressBar(b: Boolean) {
        if(b) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    private fun showMessage(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }

    private fun updateUI(){
        findNavController().navigate(R.id.action_identificationFragment_to_taxiFragment)
    }

}