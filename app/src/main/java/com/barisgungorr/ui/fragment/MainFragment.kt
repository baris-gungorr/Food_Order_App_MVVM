package com.barisgungorr.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.data.entity.Yemekler
import com.barisgungorr.ui.adapter.HomeCardAdapter
import com.barisgungorr.ui.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding

    private val viewModel: MainViewModel by viewModels()

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
        val adapter = HomeCardAdapter(viewModel, requireContext(), mealList)
        binding.Rv.adapter = adapter
        binding.progressBar.isGone = true
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun initViews() {
        binding.Rv.layoutManager = GridLayoutManager(requireContext(), 3)

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

        binding.imageViewLogOut.setOnClickListener {

            val message = AlertDialog.Builder(requireContext())
            message.setMessage(R.string.AreYouExit)
            message.setTitle(R.string.setTitleAlert)
            message.setIcon(R.drawable.app_icon)

            message.setPositiveButton(R.string.buttonYes) { _, _ ->
                viewModel.signOut()
                findNavController().navigate(R.id.mainToSign)
                Toast.makeText(requireContext(), R.string.textLogOut, Toast.LENGTH_SHORT).show()

            }
            message.setNegativeButton(R.string.buttonNo) { _, _ ->

            }
            message.create().show()
        }
    }
}

