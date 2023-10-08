package com.barisgungorr.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.HomeCardBinding
import com.barisgungorr.data.entity.Yemekler
import com.bumptech.glide.Glide

class HomeCardAdapter(val mealList:List<Yemekler>) :
    RecyclerView.Adapter<HomeCardAdapter.ViewHolder>() {

    inner class ViewHolder(var binding:HomeCardBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = HomeCardBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return mealList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val meal = mealList[position]
        holder.binding.imageTitle.text = meal.yemek_adi
        holder.binding.imagePrice.text = meal.yemek_fiyat

        Glide.with(holder.itemView.context)
            .load(meal.yemek_resim_adi)
            .into(holder.binding.imageMeal)

    }

}
