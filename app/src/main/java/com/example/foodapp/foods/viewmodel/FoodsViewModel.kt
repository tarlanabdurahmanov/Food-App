package com.example.foodapp.foods.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.foods.model.Meal
import com.example.foodapp.foods.usecase.IGetFoodsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject


sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: List<Meal>) : ViewState()
    data class Error(val message: String) : ViewState()
}


@HiltViewModel
class FoodsViewModel @Inject constructor(
    val useCase: IGetFoodsUseCase
) : ViewModel() {

    private val _viewState: MutableState<ViewState> = mutableStateOf(ViewState.Loading)
    val viewState: State<ViewState> = _viewState



    fun getFoodsForCategory(categoryName: String) {
        viewModelScope.launch {
            try {
                val listFoods = useCase(categoryName)
                _viewState.value = ViewState.Success(listFoods.meals)

            } catch (e: Exception) {
                Log.d("BK", "EXCEPTION-> ${e.message}")
                _viewState.value = ViewState.Error(e.message ?: "Unknown error")
            }
        }
    }


}