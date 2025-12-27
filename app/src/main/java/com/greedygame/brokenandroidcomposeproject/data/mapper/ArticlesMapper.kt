package com.greedygame.brokenandroidcomposeproject.data.mapper

import com.greedygame.brokenandroidcomposeproject.data.dto.ArticlesDataDto
import com.greedygame.brokenandroidcomposeproject.data.dto.ArticlesRequestDto
import com.greedygame.brokenandroidcomposeproject.data.dto.ArticlesResponseDto
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesData
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesRequest
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesResponse

/** Request **/
fun ArticlesRequest.toArticlesRequestDto(): ArticlesRequestDto= ArticlesRequestDto(q=platform)

/** Response **/

fun ArticlesResponseDto.toArticlesResponseDto(): ArticlesResponse = ArticlesResponse(articles= articles.map { it.toArticlesData() })

fun ArticlesDataDto.toArticlesData(): ArticlesData = ArticlesData(author=author,title=title,description=description)