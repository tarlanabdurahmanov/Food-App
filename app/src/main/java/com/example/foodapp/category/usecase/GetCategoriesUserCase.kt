package com.example.foodapp.category.usecase

import com.example.foodapp.category.model.CategoryResponse
import com.example.foodapp.category.repository.ICategoryRepository
import javax.inject.Inject

interface IGetCategoriesUserCase {
   suspend operator fun invoke(): CategoryResponse
}

class GetCategoriesUserCase @Inject constructor(
    val repo: ICategoryRepository
) : IGetCategoriesUserCase {

    override suspend fun invoke(): CategoryResponse {
        return repo.getAllCategories()
    }

}