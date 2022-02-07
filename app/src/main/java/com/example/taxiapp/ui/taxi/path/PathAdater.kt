package com.example.taxiapp.ui.taxi.path

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taxiapp.R
import com.example.taxiapp.data.OrderPath
import com.example.taxiapp.data.OrderRate
import com.example.taxiapp.databinding.ItemTaxiPathBinding

class PathAdater : RecyclerView.Adapter<PathAdater.ListViewHolder>(){

    var items: MutableList<OrderPath> = mutableListOf()

    private var onItemClick: (item: OrderPath) -> Unit = {}
    fun setOnClickListener(onItemClick: (item: OrderPath) -> Unit) {
        this.onItemClick = onItemClick
    }

    private var onPhoneClick: (phone: String) -> Unit = {}
    fun setOnPhoneClickListener(onPhoneClick: (phone: String) -> Unit) {
        this.onPhoneClick = onPhoneClick
    }

    inner class ListViewHolder(private val binding: ItemTaxiPathBinding): RecyclerView.ViewHolder(binding.root){

        fun populateModel(item: OrderPath) {
            binding.apply {
                tvAdress.text = item.adress
                tvPhone.text = item.phone
                tvComent.text = item.coment
                tvSum.text = item.sum
            }
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
            binding.ivPhone.setOnClickListener{
                onPhoneClick.invoke(item.phone)
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PathAdater.ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_taxi_path, parent, false)
        val binding = ItemTaxiPathBinding.bind(view)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PathAdater.ListViewHolder, position: Int) {
        holder.populateModel(items[position])
    }

    override fun getItemCount(): Int = items.size

}