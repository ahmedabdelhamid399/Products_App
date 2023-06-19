package com.example.productsonline

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET

interface ApiService
{
    @GET("products")
    fun getProducts(): Call<ResponseBody>
}