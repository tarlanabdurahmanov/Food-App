package com.example.foodapp.foods.repository

import com.example.foodapp.foods.model.FoodsResponse
import com.example.foodapp.foods.service.IFoodsService
import javax.inject.Inject


interface IFoodsRepository {
    suspend fun getFoodsForCategory(categoryName: String): FoodsResponse
}

class FoodsRepository @Inject constructor(
    val service: IFoodsService
) : IFoodsRepository {

    override suspend fun getFoodsForCategory(categoryName: String): FoodsResponse {
        return service.getFoodsForCategory(categoryName)
    }
}