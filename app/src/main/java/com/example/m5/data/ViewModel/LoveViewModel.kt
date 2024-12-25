package com.example.m5.data.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.m5.Constants
import com.example.m5.data.model.LoveModel
import com.example.m5.data.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Response

class LoveViewModel : ViewModel() {

    val data = MutableLiveData<LoveModel?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun onCalculateClick(firstName: String, secondName: String) {
        loading.value = true
        RetrofitInstance.api.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = Constants.API_KEY,
            host = Constants.API_HOST
        ).enqueue(object : retrofit2.Callback<LoveModel> {

            override fun onResponse(
                call: Call<LoveModel>,
                response: Response<LoveModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val loveModel = response.body()!!
                    data.value = loveModel
                } else {
                    error.value = response.message()
                }
                loading.value = false
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                error.value = t.message
                loading.value = false
            }

        })

    }
}