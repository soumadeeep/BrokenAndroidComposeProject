package com.greedygame.brokenandroidcomposeproject.domain.usecase

import com.greedygame.brokenandroidcomposeproject.data.repository.GetArticlesRepository
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesRequest
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesResponse
import javax.inject.Inject

class ArticlesImpl @Inject constructor(private val getArticlesRepository: GetArticlesRepository ):
    ArticlesRepository {
    override suspend fun getArticles(platform: ArticlesRequest): Result<ArticlesResponse> {
       return getArticlesRepository.getArticles(platform)
    }
}