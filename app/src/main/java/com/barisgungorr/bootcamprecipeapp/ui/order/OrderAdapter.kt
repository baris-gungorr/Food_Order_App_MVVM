package com.barisgungorr.bootcamprecipeapp.ui.order

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewOrderCardBinding

class OrderAdapter(
    private val mealList: List<Basket>,
    private val callbacks: OrderCallbacks
) : RecyclerView.Adapter<OrderViewHolder>() {

    interface OrderCallbacks {
        fun onDeleteOrder(basket : Basket)
        fun onDecreaseOrderQuantity(basket: Basket)
        fun onIncreaseOrderQuantity(basket: Basket)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val binding =
            ItemViewOrderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OrderViewHolder(binding, callbacks)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(viewHolder: OrderViewHolder, position: Int) {
        val basket = mealList[position]
        viewHolder.bind(basket)
    }
}

