package com.barisgungorr.bootcamprecipeapp.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.view.isGone
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.MealResponse
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    private val adapter by lazy {
        HomeAdapter(object : HomeAdapter.FoodCallback {
            override fun onClickDetail(food: MealResponse) {
                findNavController().navigate(
                    HomeFragmentDirections.actionMainFragmentToDetailsFragment(
                        meal = food
                    )
                )
            }
        })
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() = with(binding) {
        rvHome.layoutManager = GridLayoutManager(requireContext(), 3)
        binding.rvHome.adapter = adapter

        ivLogout.setOnClickListener {
            AlertDialog.Builder(requireContext()).apply {
                setMessage(R.string.home_page_are_you_exit)
                setTitle(R.string.home_page_set_title_alert)
                setIcon(R.drawable.ic_app_icon)
                setPositiveButton(R.string.home_page_button_yes) { _, _ ->
                    viewModel.signOut()
                    findNavController().navigate(R.id.mainToSign)
                }
                setNegativeButton(R.string.home_page_button_no) { _, _ -> }
            }.show()
        }
        etSearch.addTextChangedListener {
            viewModel.search(it.toString())
        }
    }
    private fun observe() {
        viewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            binding.progressBar.isGone = true
            adapter.submitList(mealList)
        }
    }
}


