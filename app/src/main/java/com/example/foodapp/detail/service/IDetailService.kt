package com.example.foodapp.detail.service

import com.example.foodapp.detail.model.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface IDetailService {


    @GET("lookup.php")
    suspend fun getDetailsForFood(@Query("i") mealId: String): DetailResponse


}