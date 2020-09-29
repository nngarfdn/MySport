package com.example.mysport.network

import com.example.mysport.model.News
import retrofit2.Call
import retrofit2.http.GET

interface KabarService {

    @GET("v2/top-headlines?country=id&category=sports&apiKey=9768f48a22d5466994aad0ce84ac7688")
    fun getSportNews() : Call<News>

}