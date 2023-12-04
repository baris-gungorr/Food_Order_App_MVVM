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
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.data.retrofit.response.Meal
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentHomeBinding
import com.barisgungorr.bootcamprecipeapp.utils.extension.click
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: HomeAdapter

    private val viewModel: HomeViewModel by viewModels()

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

    private fun observe() {
        viewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            binding.progressBar.isGone = true
            initMealList(mealList)
        }
    }

    private fun initMealList(mealList: List<Meal>) {
        adapter = HomeAdapter(mealList, object : HomeAdapter.FoodCallback {
            override fun onClickDetail(meal: Meal) {
                findNavController().navigate(
                    HomeFragmentDirections.actionMainFragmentToDetailsFragment(
                        meal = meal
                    )
                )
            }
        })
        binding.rvHome.adapter = adapter
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() = with(binding) {
        rvHome.layoutManager = GridLayoutManager(requireContext(), 3)

        searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return false
            }
        })

        searchView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                searchView.isIconified = false
            }
            false
        }

        imageViewLogOut.click {
            AlertDialog.Builder(requireContext()).apply {
                setMessage(R.string.areYouExit)
                setTitle(R.string.setTitleAlert)
                setIcon(R.drawable.ic_app_icon)
                setPositiveButton(R.string.buttonYes) { _, _ ->
                    viewModel.signOut()
                    findNavController().navigate(R.id.mainToSign)
                }
                setNegativeButton(R.string.buttonNo) { _, _ -> }
            }.show()

        }
    }
}


