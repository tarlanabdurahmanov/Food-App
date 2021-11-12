package com.example.foodapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.foodapp.category.CategoryScreen
import com.example.foodapp.detail.DetailScreen
import com.example.foodapp.foods.FoodsScreen
import com.example.foodapp.ui.theme.FoodAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FoodAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    FoodApp()
                }
            }
        }
    }
}


@Composable
fun FoodApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "categories") {
        composable("categories") {
            CategoryScreen { category ->
                navController.navigate("foods/${category}")
            }
        }
        composable("foods/{category}", arguments = listOf(navArgument("category") {
            type = NavType.StringType
        })) {
            val categoryStr = remember {
                it.arguments?.getString("category")
            }
            FoodsScreen(category = categoryStr) { mealId ->
                navController.navigate("detail/${mealId}")
            }
        }

        composable("detail/{mealid}", arguments = listOf(navArgument("mealid") {
            type = NavType.StringType
        })) {
            val mealStrId = remember {
                it.arguments?.getString("mealid")
            }
            DetailScreen(mealId = mealStrId)
        }
    }
//    FoodsScreen()
}
