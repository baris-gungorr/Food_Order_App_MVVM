package com.barisgungorr.bootcamprecipeapp.ui.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewOrderCardBinding

class CardAdapter(
    private val mealList: List<BasketMealResponse>,
    private val callbacks: OrderCallbacks
) : RecyclerView.Adapter<CardViewHolder>() {

    interface OrderCallbacks {
        fun onDeleteOrder(basket : BasketMealResponse)
        fun onDecreaseOrderQuantity(basket: BasketMealResponse)
        fun onIncreaseOrderQuantity(basket: BasketMealResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding =
            ItemViewOrderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding, callbacks)
    }

    override fun getItemCount(): Int = mealList.size

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        val basket = mealList[position]
        viewHolder.bind(basket)
    }
}

