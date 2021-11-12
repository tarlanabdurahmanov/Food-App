package com.example.foodapp.detail.repository

import com.example.foodapp.detail.model.DetailResponse
import com.example.foodapp.detail.service.IDetailService
import javax.inject.Inject

interface IDetailRepository {
    suspend fun getDetailForFood(id: String): DetailResponse
}

class DetailRepository @Inject constructor(
    val service: IDetailService
) : IDetailRepository {
    override suspend fun getDetailForFood(id: String): DetailResponse {
        return service.getDetailsForFood(mealId = id)
    }
}