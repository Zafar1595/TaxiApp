package uz.taxi.taxiapp.ui.taxi

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.taxiapp.databinding.FragmentTaxiBinding
import com.google.android.material.tabs.TabLayoutMediator


class TaxiFragment : Fragment() {

    lateinit var adapter: ViewPagerAdapter

    private lateinit var binding: FragmentTaxiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTaxiBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = ViewPagerAdapter(requireActivity().supportFragmentManager, lifecycle)
        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){tab, pos ->
            if(pos == 0){
                tab.text = "Тариф"
            }else{
                tab.text = "По пути"
            }
        }.attach()


    }

}