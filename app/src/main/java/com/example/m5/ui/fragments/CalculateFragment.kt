package com.example.m5.ui.fragments

import android.animation.ObjectAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.m5.Module
import com.example.m5.R
import com.example.m5.data.ViewModel.LoveViewModel
import com.example.m5.databinding.FragmentCalculateBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CalculateFragment : Fragment()   {

    private var _binding: FragmentCalculateBinding? = null
    private val binding get() = _binding!!
    private val viewModel : LoveViewModel by viewModels()
//    private val viewModel by lazy { ViewModelProvider(this)[LoveViewModel::class.java] }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calculateBtn.setOnClickListener {
            viewModel.onCalculateClick(
                binding.firstName.text.toString(),
                binding.secondName.text.toString()
            )

        }

        viewModel.data.observe(viewLifecycleOwner) { loveModel ->
            if (loveModel != null) {
                binding.firstName.text.clear()
                binding.secondName.text.clear()
                findNavController().navigate(
                    CalculateFragmentDirections.actionCalculateFragmentToResultFragment(loveModel)
                )
                viewModel.data.value = null
            }
        }

        viewModel.error.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        }
        viewModel.loading.observe(viewLifecycleOwner) { loading ->
            if (loading) {
                binding.apply {
                    progressIndicator.visibility = View.VISIBLE
                    calculateBtn.visibility = View.GONE
                    val animation = ObjectAnimator.ofInt(progressIndicator, "progress", 0, 90)
                    animation.interpolator = LinearInterpolator()
                    animation.start()
                    dimContainer.visibility = View.VISIBLE
                }
            } else {
                binding.apply {
                    dimContainer.visibility = View.GONE
                    progressIndicator.visibility = View.GONE
                    calculateBtn.visibility = View.VISIBLE
                }
            }
        }
        setUpListeners()

    }

    private fun setUpListeners(){
        binding.btnHistory.setOnClickListener(){
            findNavController().navigate(R.id.action_calculateFragment_to_history)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}