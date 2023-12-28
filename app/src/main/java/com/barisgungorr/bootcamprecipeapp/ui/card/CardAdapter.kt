package com.barisgungorr.bootcamprecipeapp.ui.card

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewOrderCardBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.load

class CardAdapter(
    private val callbacks: OrderCallbacks
) : ListAdapter<BasketMealResponse, CardViewHolder>(CardDiffCallback()) {

    interface OrderCallbacks {
        fun onDeleteOrder(basket : BasketMealResponse)
        fun onDecreaseOrderQuantity(basket: BasketMealResponse)
        fun onIncreaseOrderQuantity(basket: BasketMealResponse)
    }

    class CardDiffCallback : DiffUtil.ItemCallback<BasketMealResponse>() {
        override fun areItemsTheSame(oldItem: BasketMealResponse, newItem: BasketMealResponse): Boolean {
            return oldItem.imageName == newItem.imageName
        }

        override fun areContentsTheSame(oldItem: BasketMealResponse, newItem: BasketMealResponse): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val binding =
            ItemViewOrderCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewHolder(binding, callbacks)
    }

    override fun onBindViewHolder(viewHolder: CardViewHolder, position: Int) {
        val basket = getItem(position)
        viewHolder.bind(basket)
    }
}

