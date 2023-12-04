package com.barisgungorr.bootcamprecipeapp.ui.order

import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewOrderCardBinding
import com.bumptech.glide.Glide

class OrderViewHolder(
    private val binding: ItemViewOrderCardBinding,
    private val callbacks: OrderAdapter.OrderCallbacks
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(basket: Basket) = with(binding) {

        val context = binding.root.context

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basket.mealsImageName}"

        Glide.with(context).load(url).into(binding.imageOrder)

        OrderMealsName.text = basket.mealsName
        OrderMealsPrice.text = "${basket.mealsPrice * basket.mealsOrderPiece} ₺"
        PieceText.text = "${basket.mealsOrderPiece}"

        OrderDeleteImage.setOnClickListener {
            callbacks.onDeleteOrder(basket = basket)
        }

        ButtonMinus.setOnClickListener {
            callbacks.onDecreaseOrderQuantity(basket)
        }

        ButtonPlus.setOnClickListener {
            callbacks.onIncreaseOrderQuantity(basket)
        }
    }
}