package com.greedygame.brokenandroidcomposeproject.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.greedygame.brokenandroidcomposeproject.ui.screens.DetailScreen
import com.greedygame.brokenandroidcomposeproject.ui.screens.NewsScreen
import com.greedygame.brokenandroidcomposeproject.ui.screens.NewsViewmodel
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun NavigationHost(onTitleChange: (String) -> Unit) {
    val navController = rememberNavController()
    val newsViewmodel: NewsViewmodel = hiltViewModel()
    NavHost(navController = navController, startDestination = Screen.NewsScreen.route) {
        composable(Screen.NewsScreen.route) {
            onTitleChange("News")
            NewsScreen(navController, newsViewmodel)
        }
        composable(
            Screen.DetailScreen.route, arguments = listOf(
            navArgument("title") { type = NavType.StringType },
            navArgument("description") { type = NavType.StringType }
        )) { backStackEntry ->
            onTitleChange("Details")
            DetailScreen(
                backStackEntry.arguments?.getString("title") ?: "",
                backStackEntry.arguments?.getString("description") ?: ""
            )
        }
    }
}