package com.example.mysport.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysport.model.News
import com.example.mysport.network.ConfigNetwork
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SportViewModel : ViewModel(){

    var result : MutableLiveData<News> = MutableLiveData()
    var newssError : MutableLiveData<Boolean> = MutableLiveData()
    var loading : MutableLiveData<Boolean> = MutableLiveData()

    private val configNetwork : ConfigNetwork = ConfigNetwork()

    fun getResults() : LiveData<News> = result

    fun getError() : LiveData<Boolean> = newssError

    fun getLoading() : LiveData<Boolean> = loading

    fun loadResults() {
        loading.value = true
        val call = configNetwork.getResultsSportNews()
        call.enqueue(object : Callback<News>{
            override fun onFailure(call: Call<News>, t: Throwable) {
                newssError.value = true
                loading.value = false
                Log.e("error server", t.message!!)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                response.isSuccessful.let {
                    loading.value = false
                    val resultList = News(response.body()?.articles ?: emptyList())
                    result.value = resultList
                }
            }

        })
    }

}