package com.example.foodapp.category.service

import com.example.foodapp.category.model.CategoryResponse
import retrofit2.http.GET

interface ICategoryService {
    @GET("categories.php")
    suspend fun getAllCategories(): CategoryResponse
}

//https://www.themealdb.com/api/json/v1/1/categories.php