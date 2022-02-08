package com.example.taxiapp.ui.taxi.rate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.data.OrderRate
import com.example.taxiapp.databinding.ItemTaxiRateBinding

class RateAdapter : RecyclerView.Adapter<RateAdapter.ListViewHolder>() {

    var items: MutableList<OrderRate> = mutableListOf()
    set(value) {
        field = value
    }


    private var onItemClick: (item: OrderRate) -> Unit = {}
    fun setOnClickListener(onItemClick: (item: OrderRate) -> Unit) {
        this.onItemClick = onItemClick
    }

    private var onPhoneClick: (phone: String) -> Unit = {}
    fun setOnPhoneClickListener(onPhoneClick: (phone: String) -> Unit) {
        this.onPhoneClick = onPhoneClick
    }
    inner class ListViewHolder(private val binding: ItemTaxiRateBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun populateModel(item: OrderRate) {
            binding.apply {
                tvAdress.text = item.adress
                tvPhone.text = item.phone
                tvComent.text = item.coment
                tvDistance.text = "${item.distance} км"
            }
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
            binding.ivPhone.setOnClickListener{
                onPhoneClick.invoke(item.phone)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_taxi_rate, parent, false)
        val binding = ItemTaxiRateBinding.bind(view)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(items[position])
    }

    override fun getItemCount(): Int = items.size

}