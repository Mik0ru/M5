package com.example.m5.data.presenter

import com.example.m5.Constants
import com.example.m5.data.model.LoveModel
import com.example.m5.data.network.RetrofitInstance
import com.example.m5.data.view.LoveView
import retrofit2.Call
import retrofit2.Response

class LovePresenter {
    private var loveView: LoveView? = null

    fun attachView(loveView: LoveView) {
        this.loveView = loveView
    }
    fun detachView() {
        loveView = null
    }

    fun onCalculateClick(firstName: String, secondName: String){
        loveView?.showLoading(true)
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
                    loveView?.showLoading(false)
                    loveView?.showResult(loveModel)
                }
                else{
                    loveView?.showLoading(false)
                    loveView?.showToast("Error")
                }
            }

            override fun onFailure(call: Call<LoveModel>, t: Throwable) {
                loveView?.showLoading(false)
                loveView?.showToast(t.message.toString())
            }
        })
    }
}