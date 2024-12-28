package com.example.m5.data.ViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.m5.Constants
import com.example.m5.data.local.HistoryDao
import com.example.m5.data.local.HistoryDatabase
import com.example.m5.data.local.HistoryEntity
import com.example.m5.data.network.model.LoveModel
import com.example.m5.data.network.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

@HiltViewModel
class LoveViewModel @Inject constructor(
   private val apiService: ApiService,
   private val historyDao: HistoryDao,
    private val historyDatabase: HistoryDatabase
) : ViewModel() {

    val data = MutableLiveData<LoveModel?>()
    val error = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    fun onCalculateClick(firstName: String, secondName: String) {
        loading.value = true
        apiService.getPercentage(
            firstName = firstName,
            secondName = secondName,
            key = Constants.API_KEY,
            host = Constants.API_HOST
        ).enqueue(object : Callback<LoveModel> {

            override fun onResponse(
                call: Call<LoveModel>,
                response: Response<LoveModel>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val loveModel = response.body()!!
                    data.value = loveModel
                    historyDatabase.historyDao().insertHistory(
                        HistoryEntity(loveModel.firstName,loveModel.secondName,
                        loveModel.percentage,loveModel.result)
                    )


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