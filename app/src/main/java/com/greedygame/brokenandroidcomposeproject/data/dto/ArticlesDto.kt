package com.greedygame.brokenandroidcomposeproject.data.dto

data class ArticlesRequestDto(val q:String)
data class ArticlesResponseDto(val articles:List<ArticlesDataDto>)
data class ArticlesDataDto(
    val author: String?,
    val title:String?,
    val description: String?
)