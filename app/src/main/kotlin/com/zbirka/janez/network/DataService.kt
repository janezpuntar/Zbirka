package com.zbirka.janez.network

import com.zbirka.janez.model.WebData
import retrofit2.Call
import retrofit2.http.GET

interface DataService {

    @GET("/posts")
    fun getWebData(): Call<List<WebData>>

}