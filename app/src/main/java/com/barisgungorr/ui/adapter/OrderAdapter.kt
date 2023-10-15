package com.barisgungorr.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.databinding.CardDesignBinding
import com.barisgungorr.data.entity.Sepetler
import com.barisgungorr.ui.viewmodel.OrderViewModel
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.play.integrity.internal.t

class OrderAdapter(var mContext: Context,
                   var mealList: List<Sepetler>,
                   var viewmodel: OrderViewModel)
    : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {
    inner class ViewHolder(var binding: CardDesignBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CardDesignBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val k = holder.binding
        val basket = mealList[position]

        if (mealList.size > 0) {
            k.OrderMealsName.text = "${basket.yemek_adi}"
            k.OrderMealsPrice.text = "${basket.yemek_fiyat * basket.yemek_siparis_adet} ₺"
            k.PieceText.text = "${basket.yemek_siparis_adet}"

            val url = "http://kasimadalan.pe.hu/yemekler/resimler/${basket.yemek_resim_adi}"
            Glide.with(mContext).load(url).into(k.imageOrder)
            k.OrderDeleteImage.setOnClickListener {
                Snackbar.make(it, "${basket.yemek_adi} Remove From Card ?", Snackbar.LENGTH_LONG
                ).setAction("YES") {
                    viewmodel.delete(basket.sepet_yemek_id, basket.kullanici_adi)

                    removeBasket(position)

                }.show()
            }


            k.ButtonMinus.setOnClickListener {
                if (basket.yemek_siparis_adet > 1) {
                    basket.yemek_siparis_adet--
                    k.PieceText.text = "${basket.yemek_siparis_adet}"
                    k.OrderMealsPrice.text = "${basket.yemek_siparis_adet * basket.yemek_fiyat} ₺"

                }
            }

            k.ButtonPlus.setOnClickListener {
                basket.yemek_siparis_adet ++
                k.PieceText.text = "${basket.yemek_siparis_adet}"
                k.OrderMealsPrice.text = "${basket.yemek_siparis_adet * basket.yemek_fiyat} ₺"
            }

        } else {
            holder.itemView.visibility = View.GONE
        }

    }

    fun removeBasket(position: Int) {
      //  mealList.removeAt(position)
        notifyItemRemoved(position)
        if (mealList.isEmpty()) {

        } else if (position == mealList.size) {
            calculatePrice()
        }
    }

    private fun calculatePrice(): Double {
        var totalPrice = 0.0
        for (item in mealList) {
            totalPrice += item.yemek_fiyat * item.yemek_siparis_adet

        }
        return totalPrice
    }
}
