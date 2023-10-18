package com.barisgungorr.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.navigation.NavArgs
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteBinding
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.ui.adapter.FavoriteAdapter
import com.barisgungorr.ui.adapter.HomeCardAdapter
import com.barisgungorr.ui.adapter.OrderAdapter
import com.barisgungorr.ui.fragment.DetailsFragmentArgs
import com.barisgungorr.ui.viewmodel.FavoriteViewModel
import com.barisgungorr.ui.viewmodel.MainViewModel
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel : FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)



        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.layoutManager = layoutManager

        viewModel.mealList.observe(viewLifecycleOwner) {

            val adapter = FavoriteAdapter(viewModel,requireContext(),it)

            binding.recyclerView.adapter = adapter

        }

        binding.imageViewBack.setOnClickListener {
           Navigation.findNavController(it).navigate(R.id.favoriteToMain)

        }

        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: FavoriteViewModel by viewModels()
        viewModel = tempViewModel
    }
}