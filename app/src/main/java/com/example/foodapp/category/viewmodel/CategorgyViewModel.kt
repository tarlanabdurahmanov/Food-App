package com.example.foodapp.category.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.foodapp.category.model.Category
import com.example.foodapp.category.model.CategoryResponse
import com.example.foodapp.category.usecase.IGetCategoriesUserCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CategoryViewModel @Inject constructor(
    useCase: IGetCategoriesUserCase
) : ViewModel() {

    private val _listOfCategories: MutableState<List<Category>> = mutableStateOf(emptyList())

    val listOfCategories: State<List<Category>> = _listOfCategories

    init {
        viewModelScope.launch {
            val categoriesList = useCase()
            _listOfCategories.value = categoriesList.categories

        }
    }

}