package com.greedygame.brokenandroidcomposeproject

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import com.greedygame.brokenandroidcomposeproject.ui.navigation.NavigationHost
import com.greedygame.brokenandroidcomposeproject.ui.screens.NewsScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            var topBarTitle by remember { mutableStateOf("News") }
            Surface(modifier = Modifier.fillMaxSize()) {
                Scaffold(topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                topBarTitle,
                                color = Color.Black
                            )
                        },
                        colors = TopAppBarDefaults.topAppBarColors(
                            containerColor = Color.LightGray,
                            titleContentColor = Color.Black
                        )
                    )
                }) { paddingValues ->
                    Box(modifier = Modifier.padding(paddingValues)) {
                        NavigationHost(onTitleChange = { newTitle ->
                            topBarTitle = newTitle
                        })
                    }

                }
            }
        }
    }
}