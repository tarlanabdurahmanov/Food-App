package com.example.foodapp.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.example.foodapp.detail.model.MealDetail
import com.example.foodapp.detail.viewmodel.DetailViewModel
import com.example.foodapp.detail.viewmodel.DetailViewState

@Composable
fun DetailScreen(
    viewModel: DetailViewModel = hiltViewModel(),
    mealId: String?
) {
    DisposableEffect(key1 = Unit) {
        if (!mealId.isNullOrBlank()) {
            viewModel.getDetailForFood(id = mealId)
        }
        onDispose { }
    }

    val viewState by remember {
        viewModel.viewState
    }

    when (val state = viewState) {
        is DetailViewState.Success -> {
            DetailItem(mealDetail = state.data)
        }

        is DetailViewState.Error -> {
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
fun DetailItem(
    mealDetail: MealDetail
) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    Column {
        Image(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight / 2),
            painter = rememberImagePainter(mealDetail.strMealThumb ?: mealDetail.strImageSource),
            contentDescription = null
        )

        Column(modifier = Modifier.padding(20.dp)) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = mealDetail.strArea, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                Icon(Icons.Outlined.Favorite, contentDescription = null)
            }
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mealDetail.strCategory,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(
                text = mealDetail.strTags.toString(),
                fontSize = 12.sp,
            )
        }
    }
}