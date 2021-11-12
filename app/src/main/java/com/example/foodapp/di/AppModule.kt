package com.example.foodapp.di

import com.example.foodapp.category.repository.CategoryRepository
import com.example.foodapp.category.repository.ICategoryRepository
import com.example.foodapp.category.service.ICategoryService
import com.example.foodapp.category.usecase.GetCategoriesUserCase
import com.example.foodapp.category.usecase.IGetCategoriesUserCase
import com.example.foodapp.detail.repository.DetailRepository
import com.example.foodapp.detail.repository.IDetailRepository
import com.example.foodapp.detail.service.IDetailService
import com.example.foodapp.detail.usecase.GetDetailsUseCase
import com.example.foodapp.detail.usecase.IGetDetailsUseCase
import com.example.foodapp.foods.repository.FoodsRepository
import com.example.foodapp.foods.repository.IFoodsRepository
import com.example.foodapp.foods.service.IFoodsService
import com.example.foodapp.foods.usecase.GetFoodsUseCase
import com.example.foodapp.foods.usecase.IGetFoodsUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://www.themealdb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }


    @Provides
    @Singleton
    fun providesCategoryService(retrofit: Retrofit): ICategoryService {
        return retrofit.create(ICategoryService::class.java)
    }

    @Provides
    @Singleton
    fun providesFoodsService(retrofit: Retrofit): IFoodsService {
        return retrofit.create(IFoodsService::class.java)
    }

    @Provides
    @Singleton
    fun providesDetailService(retrofit: Retrofit): IDetailService {
        return retrofit.create(IDetailService::class.java)
    }


    @Module
    @InstallIn(SingletonComponent::class)
    interface AppModuleInt {

        @Binds
        @Singleton
        fun provideFoodsRepository(repo: FoodsRepository): IFoodsRepository

        @Binds
        @Singleton
        fun provideGetFoodsUseCase(uc: GetFoodsUseCase): IGetFoodsUseCase

        @Binds
        @Singleton
        fun provideCategoryRepository(repo: CategoryRepository): ICategoryRepository

        @Binds
        @Singleton
        fun provideGetCategoryUseCase(uc: GetCategoriesUserCase): IGetCategoriesUserCase

        @Binds
        @Singleton
        fun provideDetailRepository(repo: DetailRepository): IDetailRepository

        @Binds
        @Singleton
        fun provideGetDetailUseCase(uc: GetDetailsUseCase): IGetDetailsUseCase
    }

}