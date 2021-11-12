package com.example.foodapp.category.repository

import com.example.foodapp.category.model.CategoryResponse
import com.example.foodapp.category.service.ICategoryService
import javax.inject.Inject

interface ICategoryRepository {
    suspend fun getAllCategories(): CategoryResponse
}

class CategoryRepository @Inject constructor(
    val service: ICategoryService
) : ICategoryRepository {

    override suspend fun getAllCategories(): CategoryResponse {
        return service.getAllCategories()
    }
}