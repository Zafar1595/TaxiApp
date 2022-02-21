package uz.taxi.taxiapp.ui.client

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.taxiapp.R
import com.example.taxiapp.databinding.FragmentClientBinding

class ClientFragment : Fragment() {

    private lateinit var binding: FragmentClientBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClientBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvRate.setOnClickListener {
            findNavController().navigate(R.id.action_clientFragment_to_menuFragment)
        }
        binding.tvPath.setOnClickListener {
            findNavController().navigate(R.id.action_clientFragment_to_clientMenuPathFragment)
        }

    }

}