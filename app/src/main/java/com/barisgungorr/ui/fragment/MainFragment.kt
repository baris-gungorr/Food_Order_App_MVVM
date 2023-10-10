package com.barisgungorr.ui.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.ui.adapter.HomeCardAdapter
import com.barisgungorr.ui.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var viewModel : MainViewModel
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        binding.Rv.layoutManager = GridLayoutManager(requireContext(), 3)


        viewModel.mealList.observe(viewLifecycleOwner) {
            val adapter = HomeCardAdapter(viewModel,requireContext(),it)

            binding.Rv.adapter = adapter

        }



        binding.searchView.setOnQueryTextListener(object :OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel
    }
}