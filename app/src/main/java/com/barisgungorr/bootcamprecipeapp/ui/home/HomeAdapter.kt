package com.barisgungorr.bootcamprecipeapp.ui.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.ItemViewHomeCardBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.load

class HomeAdapter(
    private val foodCallbacks: FoodCallback
) : ListAdapter<MealResponse,HomeViewHolder>(HomeDiffCallback()) {

    interface FoodCallback {
        fun onClickDetail(food: MealResponse)
    }

    class HomeDiffCallback: DiffUtil.ItemCallback<MealResponse>() {
        override fun areItemsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
            return oldItem.imageName == newItem.imageName
        }

        override fun areContentsTheSame(oldItem: MealResponse, newItem: MealResponse): Boolean {
            return oldItem == newItem
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val binding = ItemViewHomeCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HomeViewHolder(binding, foodCallbacks)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val meal = getItem(position)
        holder.bind(meal)
    }
}
