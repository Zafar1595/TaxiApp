package uz.taxi.taxiapp.ui.taxi

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.taxi.taxiapp.ui.taxi.path.ViewPagerPathFragment
import uz.taxi.taxiapp.ui.taxi.rate.ViewPagerRateFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return if(position == 0) ViewPagerRateFragment()
        else ViewPagerPathFragment()

    }


}