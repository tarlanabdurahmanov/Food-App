package com.example.foodapp.category

import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Colors
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.foodapp.category.model.Category
import com.example.foodapp.category.viewmodel.CategoryViewModel

@Composable
fun CategoryScreen(
    viewModel: CategoryViewModel = hiltViewModel(),
    onItemClick: (String) -> Unit
) {
    val listOfCategories by remember { viewModel.listOfCategories }
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp)
                .padding(top = 40.dp),
            text = "Categories",
            fontWeight = FontWeight.Medium,
            fontSize = 22.sp,
        )
        Spacer(modifier = Modifier.height(30.dp))
        LazyColumn {
            items(listOfCategories) {
                CategoryItem(category = it) {
                    onItemClick(it)
                }
            }
        }

    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun CategoryItem(
    category: Category, onClick: (String) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp)
            .padding(horizontal = 15.dp)
            .clickable {
                onClick(category.strCategory)
            }, elevation = 6.dp
    ) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(80.dp),
                painter = rememberImagePainter(category.strCategoryThumb),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(10.dp))
            Column {
                Text(text = category.strCategory)
                Text(
                    text = category.strCategoryDescription,
                    maxLines = 2,
                    color = Color.Gray,
                    fontSize = 12.sp,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}