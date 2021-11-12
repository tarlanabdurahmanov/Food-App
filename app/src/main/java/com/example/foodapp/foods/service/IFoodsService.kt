package com.example.foodapp.foods.service

import com.example.foodapp.foods.model.FoodsResponse
import retrofit2.http.GET
import retrofit2.http.Query


interface IFoodsService {
    @GET("filter.php")
    suspend fun getFoodsForCategory(@Query("c") categoryName: String): FoodsResponse
}
