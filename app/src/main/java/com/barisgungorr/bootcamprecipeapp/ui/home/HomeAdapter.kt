package com.barisgungorr.bootcamprecipeapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Meal
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewHomeCardBinding

class HomeAdapter(
    private var meals: List<Meal>,
    private val foodCallbacks: FoodCallback
) : RecyclerView.Adapter<HomeViewHolder>() {

    interface FoodCallback {
        fun onClickDetail(food: Meal)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemViewHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, foodCallbacks)
    }

    override fun getItemCount(): Int = meals.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val meal = meals[position]
        holder.bind(meal)
    }
}
