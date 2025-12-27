package com.greedygame.brokenandroidcomposeproject.domain.model

data class ArticlesRequest(val platform: String)

data class ArticlesResponse(val articles: List<ArticlesData>)

data class ArticlesData(
    val author: String?,
    val title: String?,
    val description: String?
)
