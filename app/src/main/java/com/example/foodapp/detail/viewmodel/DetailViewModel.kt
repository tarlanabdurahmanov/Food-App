package com.example.foodapp.detail.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.detail.model.MealDetail
import com.example.foodapp.detail.usecase.IGetDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class DetailViewState {
    object Loading : DetailViewState()
    data class Success(val data: MealDetail) : DetailViewState()
    data class Error(val message: String) : DetailViewState()
}


@HiltViewModel
class DetailViewModel @Inject constructor(
    val useCase: IGetDetailsUseCase
) : ViewModel() {


    private val _viewState: MutableState<DetailViewState> = mutableStateOf(DetailViewState.Loading)
    val viewState: State<DetailViewState> = _viewState

    fun getDetailForFood(id: String) {
        viewModelScope.launch {
            try {
                val mealDetailResponse = useCase(id)
                _viewState.value = DetailViewState.Success(mealDetailResponse.meals[0])

            } catch (e: Exception) {
                Log.d("BK", "EXCEPTION-> ${e.message}")
                _viewState.value = DetailViewState.Error(e.message ?: "Unknown error")
            }
        }
    }


}