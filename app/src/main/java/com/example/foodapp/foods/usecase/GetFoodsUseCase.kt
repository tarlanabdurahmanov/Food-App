package com.example.foodapp.foods.usecase

import com.example.foodapp.foods.model.FoodsResponse
import com.example.foodapp.foods.repository.IFoodsRepository
import javax.inject.Inject


interface IGetFoodsUseCase {
    suspend operator fun invoke(categoryName: String): FoodsResponse
}

class GetFoodsUseCase @Inject constructor(
    val repo: IFoodsRepository
) : IGetFoodsUseCase {
    override suspend fun invoke(categoryName: String): FoodsResponse {
        return repo.getFoodsForCategory(categoryName)
    }
}