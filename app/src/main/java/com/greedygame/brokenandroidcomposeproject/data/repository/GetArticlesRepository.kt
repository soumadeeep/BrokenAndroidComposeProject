package com.greedygame.brokenandroidcomposeproject.data.repository

import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesRequest
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesResponse

interface GetArticlesRepository {
    suspend fun getArticles( platform: ArticlesRequest): Result<ArticlesResponse>
}