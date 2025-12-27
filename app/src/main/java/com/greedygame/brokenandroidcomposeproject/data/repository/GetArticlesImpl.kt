package com.greedygame.brokenandroidcomposeproject.data.repository

import com.greedygame.brokenandroidcomposeproject.data.mapper.toArticlesRequestDto
import com.greedygame.brokenandroidcomposeproject.data.mapper.toArticlesResponseDto
import com.greedygame.brokenandroidcomposeproject.data.source.remote.ApiService
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesRequest
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesResponse
import javax.inject.Inject

class GetArticlesImpl @Inject constructor(private val apiService: ApiService): GetArticlesRepository {
    override suspend fun getArticles(platform: ArticlesRequest): Result<ArticlesResponse> {
       return try {
           val response = apiService.getArticles(platform.toArticlesRequestDto().q)
           if (response.isSuccessful){
               response.body()?.let {
                   Result.success(it.toArticlesResponseDto())
               }?: Result.failure(Exception("Empty response body"))
           }else{
               Result.failure(Exception("Unable to connect"))
           }
       }catch (e: Exception){
           Result.failure(e)
       }
    }

}