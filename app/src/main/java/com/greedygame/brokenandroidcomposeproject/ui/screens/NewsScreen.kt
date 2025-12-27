package com.greedygame.brokenandroidcomposeproject.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.greedygame.brokenandroidcomposeproject.core.utils.ResultState
import com.greedygame.brokenandroidcomposeproject.ui.navigation.Screen


@Composable
fun NewsScreen(navController: NavController, newsViewmodel: NewsViewmodel) {

    LaunchedEffect(Unit) {
        newsViewmodel.getArticles()
    }
    val uiStateValue by newsViewmodel.uiState.collectAsState()

    /** snapshot of this value **/
    val state = uiStateValue
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp), contentAlignment = Alignment.Center
    ) {
        when (state) {
            is ResultState.Loading -> CircularProgressIndicator()
            is ResultState.Success -> {
                LazyColumn {
                    items(state.articlesList) { data ->
                        NewsCard(
                            data.title.toString(),
                            data.author.toString(),
                            data.description.toString(),
                            onClick = { title, description ->
                            navController.navigate(Screen.DetailScreen.detailScreenData(title,description))
                            })
                    }
                }
            }

            is ResultState.Failure -> ErrorScreen(state.errorMessage)
        }
    }
}

@Composable
fun NewsCard(
    title: String,
    author: String,
    description: String,
    onClick: (title: String, description: String) -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 2.dp),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
                .clickable(onClick = { onClick(title, description) })
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF1D1B20)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "By $author",
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                letterSpacing = 0.5.sp
            )
        }
    }
}

@Composable
fun ErrorScreen(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            //  Error Icon
            Icon(
                imageVector = Icons.Default.Warning,
                contentDescription = "Error Icon",
                modifier = Modifier.size(64.dp),
                tint = Color.Red.copy(alpha = 0.7f)
            )

            Spacer(modifier = Modifier.height(16.dp))

            //  Error Message
            Text(
                text = "Oops! Something went wrong",
                style = MaterialTheme.typography.titleLarge,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = message,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray,
                textAlign = TextAlign.Center
            )
        }
    }
}