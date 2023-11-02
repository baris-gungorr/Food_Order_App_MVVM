package com.barisgungorr.ui.favorite

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.core.view.isGone
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteBinding
import com.barisgungorr.ui.adapter.FavoriteAdapter
import com.barisgungorr.ui.viewmodel.FavoriteViewModel
import com.barisgungorr.utils.extension.click
import dagger.hilt.android.AndroidEntryPoint



@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private val viewModel: FavoriteViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        val layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerViewFavorites?.layoutManager = layoutManager


        viewModel.favoriteList.observe(viewLifecycleOwner) { favorites ->

            if (favorites.isEmpty()) {
                binding.imageViewNull.visibility = View.VISIBLE
                binding.textViewNull.visibility = View.VISIBLE
            }

            val adapter = FavoriteAdapter(viewModel, favorites)
            binding.recyclerViewFavorites?.adapter = adapter

        }

        binding.imageViewBack.click {
            findNavController().navigate(R.id.favoriteToMain)

        }
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.searchF(newText)
                return false
            }
        })

        binding.searchView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                binding.searchView.isIconified = false
            }
            false
        }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavorites()
    }
}