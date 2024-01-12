package com.barisgungorr.bootcamprecipeapp.ui.card

import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.BasketMealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewOrderCardBinding
import com.barisgungorr.bootcamprecipeapp.utils.constans.AppConstants
import com.barisgungorr.bootcamprecipeapp.utils.extension.load

class CardViewHolder(
    private val binding: ItemViewOrderCardBinding,
    private val callbacks: CardAdapter.OrderCallbacks
) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(basket: BasketMealResponse) = with(binding) {

        val context = binding.root.context

        val url = "${AppConstants.BASE_IMAGE_URL}${basket.imageName}"
        binding.ivMeal.load(imageUrl = url)

        tvMealName.text = basket.name
        tvPrice.text = context.getString(R.string.home_page_price, basket.price)


        tvPiece.text = "${basket.piece}"

        ivDelete.setOnClickListener {
            callbacks.onDeleteOrder(basket = basket)
        }

        btnDecrease.setOnClickListener {
            callbacks.onDecreaseOrderQuantity(basket)
        }

        btnIncrease.setOnClickListener {
            callbacks.onIncreaseOrderQuantity(basket)
        }
    }
}
