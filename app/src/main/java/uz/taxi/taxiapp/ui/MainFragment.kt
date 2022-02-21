package uz.taxi.taxiapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import com.example.taxiapp.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonClient.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_clientFragment)
        }
        binding.buttonTaxi.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_taxiFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}