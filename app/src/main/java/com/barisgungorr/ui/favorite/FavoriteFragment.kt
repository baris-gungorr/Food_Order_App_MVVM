package com.barisgungorr.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentFavoriteBinding
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.ui.viewmodel.FavoriteViewModel
import com.barisgungorr.ui.viewmodel.MainViewModel


class FavoriteFragment : Fragment() {

    private lateinit var binding: FragmentFavoriteBinding
    private lateinit var viewModel : FavoriteViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: FavoriteViewModel by viewModels()
        viewModel = tempViewModel
    }

}