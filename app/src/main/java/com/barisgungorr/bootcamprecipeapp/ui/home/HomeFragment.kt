package com.barisgungorr.bootcamprecipeapp.ui.home

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.bootcamprecipeapp.data.entity.Yemekler
import com.barisgungorr.bootcamprecipeapp.utils.extension.click
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: HomeAdapter
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        observe()

    }

    private fun observe() {
        viewModel.mealList.observe(viewLifecycleOwner) { mealList ->
            initMealList(mealList)
        }
    }

    private fun initMealList(mealList: List<Yemekler>) {
        adapter = HomeAdapter(mealList, object : HomeAdapter.FoodCallback {
            override fun onClickDetail(meal: Yemekler) {
               val bundle =  Bundle().apply {
                    putSerializable("meal",meal)
                }
                findNavController().navigate(R.id.action_mainFragment_to_detailsFragment, bundle)
            }
        })
        binding.RecyclerViewMain.adapter = adapter
        binding.progressBar.isGone = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {
        binding.RecyclerViewMain.layoutManager = GridLayoutManager(requireContext(), 3)

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewModel.search(newText)
                return false
            }
        })

        binding.searchView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                binding.searchView.isIconified = false
            }
            false
        }

        binding.imageViewLogOut.click {

            val message = AlertDialog.Builder(requireContext())
            message.setMessage(R.string.areYouExit)
            message.setTitle(R.string.setTitleAlert)
            message.setIcon(R.drawable.ic_app_icon)

            message.setPositiveButton(R.string.buttonYes) { _, _ ->
                viewModel.signOut()
                findNavController().navigate(R.id.mainToSign)
            }
            message.setNegativeButton(R.string.buttonNo) { _, _ ->

            }
            message.create().show()
        }
    }
}