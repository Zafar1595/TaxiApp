package uz.taxi.taxiapp.firebase

import android.util.Log
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.FirebaseFirestore
import uz.taxi.taxiapp.data.OrderPath
import uz.taxi.taxiapp.data.OrderRate

class FirebaseManager(private val instance: FirebaseFirestore) {

    companion object {
        const val TAXI = "Taxies"
        const val ORDER_PATH = "OrderPath"
        const val ORDER_RATE = "OrdersRate"
    }

   /* fun getOrdersRate(
        onSuccess: (orderRateList: List<OrderRate>) -> Unit,
        onError: (message: String) -> Unit
    ) {
        val orderRateList: MutableList<OrderRate> = mutableListOf()
        instance.collection(ORDER_RATE).get().addOnSuccessListener {
            it.documents.forEach { document ->
                Log.d("TAG", document.toString())
                document.toObject(OrderRate::class.java)?.let { order -> orderRateList.add(order) }
            }
            onSuccess.invoke(orderRateList)
        }.addOnFailureListener {
            onError.invoke(it.localizedMessage)
        }
    }*/

    fun observeOrderRate(
        onOrderRateAdded: (orderRate: OrderRate) -> Unit,
        onOrderRateModefied: (orderRate: OrderRate) -> Unit,
        onOrderRateRemoved: (orderRate: OrderRate) -> Unit
    ) {
        instance.collection(ORDER_RATE).addSnapshotListener { querySnapshot, _ ->
            querySnapshot?.let {
                for (document in it.documentChanges) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            onOrderRateAdded.invoke(document.document.toObject(OrderRate::class.java))
                        }
                        DocumentChange.Type.MODIFIED -> {
                            onOrderRateModefied.invoke(document.document.toObject(OrderRate::class.java))
                        }
                        DocumentChange.Type.REMOVED -> {
                            onOrderRateRemoved.invoke(document.document.toObject(OrderRate::class.java))
                        }
                    }
                }
            }
        }

    }

    fun observeOrderPath(
        onOrderRateAdded: (orderRate: OrderPath) -> Unit,
        onOrderRateModefied: (orderRate: OrderPath) -> Unit,
        onOrderRateRemoved: (orderRate: OrderPath) -> Unit
    ) {
        instance.collection(ORDER_PATH).addSnapshotListener { querySnapshot, _ ->
            querySnapshot?.let {
                for (document in it.documentChanges) {
                    when (document.type) {
                        DocumentChange.Type.ADDED -> {
                            onOrderRateAdded.invoke(document.document.toObject(OrderPath::class.java))
                        }
                        DocumentChange.Type.MODIFIED -> {
                            onOrderRateModefied.invoke(document.document.toObject(OrderPath::class.java))
                        }
                        DocumentChange.Type.REMOVED -> {
                            onOrderRateRemoved.invoke(document.document.toObject(OrderPath::class.java))
                        }
                    }
                }
            }
        }

    }

    fun sendOrderPath(orderPath: OrderPath, onSuccess: () -> Unit, onFailur: (message: String) -> Unit){
        instance.collection(ORDER_PATH).add(orderPath)
            .addOnSuccessListener {
                Log.d("added", it.id)
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailur.invoke(it.localizedMessage)
            }
    }


    fun sendOrderRate(orderRate: OrderRate, onSuccess: () -> Unit, onFailur: (message: String) -> Unit){
        instance.collection(ORDER_RATE).add(orderRate)
            .addOnSuccessListener {
                Log.d("added", it.id)
                onSuccess.invoke()
            }
            .addOnFailureListener {
                onFailur.invoke(it.localizedMessage)
            }
    }
}