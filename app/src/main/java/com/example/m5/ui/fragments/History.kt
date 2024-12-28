package com.example.m5.ui.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.m5.R
import com.example.m5.data.local.HistoryDao
import com.example.m5.data.local.HistoryDatabase
import com.example.m5.databinding.FragmentCalculateBinding
import com.example.m5.databinding.FragmentHistoryBinding
import com.example.m5.ui.adapters.HistoryAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject




@AndroidEntryPoint
class History () : Fragment() {

    private var _binding: FragmentHistoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HistoryAdapter
    @Inject
    lateinit var database: HistoryDatabase


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialise()
        setUpListeners()
    }

    private fun initialise() {
        lifecycleScope.launch{
            val data = database.historyDao().getAll()
            binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
            binding.rvHistory.adapter = HistoryAdapter(data)
        }
       // binding.rvHistory.layoutManager = LinearLayoutManager(requireContext())
       // binding.rvHistory.adapter = HistoryAdapter(data)
    }

    private fun setUpListeners(){
        binding.btnHome.setOnClickListener(){
            findNavController().navigate(R.id.action_history_to_calculateFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}