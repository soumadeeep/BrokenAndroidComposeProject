package com.greedygame.brokenandroidcomposeproject.ui.navigation

sealed class Screen(val route:String){
    object NewsScreen: Screen("news_screen")
    object DetailScreen: Screen("detail_screen/{title}/{description}"){
        fun detailScreenData(title: String,description: String) = "detail_screen/$title/$description"
    }
}