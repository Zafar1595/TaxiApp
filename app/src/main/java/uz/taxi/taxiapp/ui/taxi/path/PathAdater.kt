package uz.taxi.taxiapp.ui.taxi.path

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.taxiapp.R
import uz.taxi.taxiapp.data.OrderPath
import com.example.taxiapp.databinding.ItemTaxiPathBinding
import uz.taxi.taxiapp.data.OrderRate

class PathAdater : RecyclerView.Adapter<PathAdater.ListViewHolder>(){

    var items: MutableList<OrderPath> = mutableListOf()

    fun add(orderPath: OrderPath){
        items.add(0, orderPath)
        notifyItemInserted(0)
    }

    fun modified(orderPath: OrderPath){
        for(i in items.indices){
            if(items[i].id == orderPath.id){
                items[i] = orderPath
                notifyItemChanged(i)
                break
            }
        }
    }

    fun removed(orderPath: OrderPath){
        for(i in items.indices){
            if (items[i].id == orderPath.id){
                items.remove(orderPath)
                notifyItemRemoved(i)
                break
            }
        }

    }


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
                tvPhone.text = item.clientPhone
                tvComent.text = item.comment
                tvSum.text = "${item.sum} сум"
            }
            itemView.setOnClickListener {
                onItemClick.invoke(item)
            }
            binding.ivPhone.setOnClickListener{
                onPhoneClick.invoke(item.clientPhone.toString())
            }
        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_taxi_path, parent, false)
        val binding = ItemTaxiPathBinding.bind(view)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.populateModel(items[position])
    }

    override fun getItemCount(): Int = items.size

}