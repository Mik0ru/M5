package com.example.m5.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.m5.Constants
import com.example.m5.R
import com.example.m5.data.CalculateResult
import com.example.m5.data.RetrofitInstance
import com.example.m5.databinding.FragmentCalculateBinding
import retrofit2.Call
import retrofit2.Response


class CalculateFragment : Fragment() {

    private var binding: FragmentCalculateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalculateBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.calculateBtn?.setOnClickListener {
            RetrofitInstance.api.getPercentage(
                firstName = binding?.firstName?.text.toString(),
                secondName = binding?.secondName?.text.toString(),
                key =  Constants.API_KEY,
                host = Constants.API_HOST
            ).enqueue(object : retrofit2.Callback<CalculateResult> {
                override fun onResponse(
                    call: Call<CalculateResult>,
                    response: Response<CalculateResult>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        binding?.firstName?.text?.clear()
                        binding?.secondName?.text?.clear()
                        findNavController().navigate(CalculateFragmentDirections.actionCalculateFragmentToResultFragment(
                            response.body()!!
                        ))
                    }
                }

                override fun onFailure(call: Call<CalculateResult>, t: Throwable) {
                    Toast.makeText(requireContext(), t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}