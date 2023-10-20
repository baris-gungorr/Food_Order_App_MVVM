package com.barisgungorr.ui.fragment

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView.OnQueryTextListener
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.barisgungorr.bootcamprecipeapp.R
import com.barisgungorr.bootcamprecipeapp.databinding.FragmentMainBinding
import com.barisgungorr.data.repo.MealsRepository
import com.barisgungorr.ui.adapter.HomeCardAdapter
import com.barisgungorr.ui.viewmodel.MainViewModel
import com.barisgungorr.utils.Extension.snackbar
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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

        binding.progressBar.visibility = View.VISIBLE

        binding.Rv.layoutManager = GridLayoutManager(requireContext(), 3)


        viewModel.mealList.observe(viewLifecycleOwner) {
            val adapter = HomeCardAdapter(viewModel,requireContext(),it)


            binding.Rv.adapter = adapter
            binding.progressBar.visibility = View.GONE

        }

        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                search(newText)
                return false
            }
        })

        binding.searchView.setOnTouchListener { _, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {

                binding.searchView.isIconified = false
            }
            false
        }

        binding.imageViewLogOut.setOnClickListener { view ->
            Snackbar.make(
                view, "Are you exiting ?", Snackbar.LENGTH_LONG

            ).setAction("YES") {
                Firebase.auth.signOut()
                findNavController().navigate(R.id.mainToSign)

            }.show()

        }

        return binding.root

        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val tempViewModel: MainViewModel by viewModels()
        viewModel = tempViewModel

    }
    fun search(searchKeyword:String){
        viewModel.search(searchKeyword)
    }

}
