package com.barisgungorr.bootcamprecipeapp.ui.order

import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Basket
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewOrderCardBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.load
import com.bumptech.glide.Glide

class OrderViewHolder(
    private val binding: ItemViewOrderCardBinding,
    private val callbacks: OrderAdapter.OrderCallbacks
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(basket: Basket) = with(binding) {

        val context = binding.root.context

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basket.imageName}"
        binding.imageOrder.load(imageUrl = url)

        OrderMealsName.text = basket.name
        OrderMealsPrice.text = context.getString(R.string.price, basket.price)


        PieceText.text = "${basket.piece}"

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