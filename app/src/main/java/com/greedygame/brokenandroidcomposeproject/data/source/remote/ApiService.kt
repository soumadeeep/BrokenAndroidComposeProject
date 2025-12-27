package com.greedygame.brokenandroidcomposeproject.data.source.remote

import com.greedygame.brokenandroidcomposeproject.BuildConfig
import com.greedygame.brokenandroidcomposeproject.data.dto.ArticlesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query



interface ApiService {
    @GET("/v2/everything")
    suspend fun getArticles(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String= BuildConfig.API_KEY
    ): Response<ArticlesResponseDto>

    /*** ====================
     * All the other apiServices we will write hare.
     */
}