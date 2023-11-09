package com.barisgungorr.bootcamprecipeapp.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentHomeCardBinding
import com.barisgungorr.bootcamprecipeapp.data.entity.Yemekler
import com.bumptech.glide.Glide

class HomeAdapter(
    private var mealList: List<Yemekler>,
    private val foodCallbacks: FoodCallback

) : RecyclerView.Adapter<HomeViewHolder>() {
    interface FoodCallback {
        fun onClickDetail(food: Yemekler)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding =
            FragmentHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, foodCallbacks)
    }

    override fun getItemCount(): Int = mealList.size


    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        val meal = mealList[position]
        holder.bind(meal)

    }
}
