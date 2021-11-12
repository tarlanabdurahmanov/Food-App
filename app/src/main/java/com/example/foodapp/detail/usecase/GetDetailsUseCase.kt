package com.example.foodapp.detail.usecase

import com.example.foodapp.detail.model.DetailResponse
import com.example.foodapp.detail.repository.IDetailRepository
import javax.inject.Inject


interface IGetDetailsUseCase {
    suspend operator fun invoke(id: String): DetailResponse
}

class GetDetailsUseCase @Inject constructor(
    val repo: IDetailRepository
) : IGetDetailsUseCase {
    override suspend fun invoke(id: String): DetailResponse {
        return repo.getDetailForFood(id = id)
    }


}