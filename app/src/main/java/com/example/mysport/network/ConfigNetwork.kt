package com.example.mysport.network


import com.example.mysport.model.News
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ConfigNetwork {

    private val service: KabarService

    companion object {
        const val BASE_URL = "https://newsapi.org/"
    }

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        service = retrofit.create(KabarService::class.java)
    }

    fun getResultsSportNews(): Call<News> {
        return service.getSportNews()
    }

}