package com.greedygame.brokenandroidcomposeproject.domain.usecase

import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesRequest
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesResponse

interface ArticlesRepository {
    suspend fun getArticles (platform: ArticlesRequest): Result<ArticlesResponse>
}