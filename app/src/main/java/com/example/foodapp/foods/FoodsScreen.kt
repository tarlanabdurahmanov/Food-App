package com.example.foodapp.foods

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.ui.*
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.foodapp.foods.model.Meal
import com.example.foodapp.foods.viewmodel.FoodsViewModel
import com.example.foodapp.foods.viewmodel.ViewState



@Composable
fun FoodsScreen(
    viewModel: FoodsViewModel = hiltViewModel(),
    category: String?,
    onFoodClick: (String) -> Unit
) {

    DisposableEffect(key1 = Unit) {
        if (!category.isNullOrBlank()) {
            viewModel.getFoodsForCategory(category)
        }
        onDispose { }
    }

    val viewState by remember {
        viewModel.viewState
    }

    when (val state = viewState) {
        is ViewState.Success -> {
            FoodsList(category, state.data, onFoodClick)
        }

        is ViewState.Error -> {
            Text(text = "Error ${state.message}")
        }
        else -> {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()

            }
        }
    }
}


@Composable
fun FoodsList(
    category: String?,
    meals: List<Meal>,
    onFoodClick: (String) -> Unit
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 40.dp),
            text = "$category Foods",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn {
            items(meals) { item ->
                FoodItem(meal = item) {
                    onFoodClick(item.idMeal)
                }
            }
        }
    }

}


@Composable
fun FoodItem(meal: Meal, onClick: (String) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .padding(horizontal = 15.dp)
            .clickable {
                onClick(meal.idMeal)
            }, elevation = 6.dp
    ) {
        Row(
            modifier = Modifier.padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = rememberImagePainter(meal.strMealThumb),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = meal.strMeal)

        }
    }
}
