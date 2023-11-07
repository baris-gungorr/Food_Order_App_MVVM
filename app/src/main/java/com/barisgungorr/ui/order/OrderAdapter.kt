package com.barisgungorr.ui.order

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentCardDesignBinding
import com.barisgungorr.data.entity.Sepetler
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

class OrderAdapter(
    private val mealList: List<Sepetler>,
    private val viewModel: OrderViewModel
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(val binding: FragmentCardDesignBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding =
            FragmentCardDesignBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = mealList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val k = holder.binding
        val basket = mealList[position]

        val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basket.meals_image_name}"

        Glide.with(holder.itemView.context).load(url).into(k.imageOrder)

        k.OrderMealsName.text = "${basket.meals_name}"
        k.OrderMealsPrice.text = "${basket.meals_price * basket.meals_order_piece} ₺"
        k.PieceText.text = "${basket.meals_order_piece}"

        k.OrderDeleteImage.setOnClickListener {
            Snackbar.make(
                it,
                "${basket.meals_name} ${it.resources.getString(R.string.removeText)}",
                Snackbar.LENGTH_LONG

            ).setAction(R.string.yesText) {
                viewModel.delete(basket.card_meals_id, basket.user_name)
                removeBasket(position)
                updateTotalPrice()
            }.show()
        }

        k.ButtonMinus.setOnClickListener {
            if (basket.meals_order_piece > 1) {
                basket.meals_order_piece--
                k.PieceText.text = "${basket.meals_order_piece}"
                k.OrderMealsPrice.text = "${basket.meals_order_piece * basket.meals_price} ₺"
                updateTotalPrice()
            }
        }

        k.ButtonPlus.setOnClickListener {
            basket.meals_order_piece++
            k.PieceText.text = "${basket.meals_order_piece}"
            k.OrderMealsPrice.text = "${basket.meals_order_piece * basket.meals_price} ₺"
            updateTotalPrice()
        }
    }

    private fun removeBasket(position: Int) {
        if (position == mealList.size - 1) {
            updateTotalPrice()
        }
    }

    private fun updateTotalPrice() {
        val totalPrice = calculatePrice()
        viewModel.totalPrice = totalPrice

    }

    private fun calculatePrice(): Int {
        var totalPrice = 0
        for (item in mealList) {
            totalPrice += item.meals_price * item.meals_order_piece
        }
        return totalPrice
    }
}

