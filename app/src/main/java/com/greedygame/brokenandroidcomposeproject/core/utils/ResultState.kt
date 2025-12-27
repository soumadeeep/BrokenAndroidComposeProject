package com.greedygame.brokenandroidcomposeproject.core.utils

import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesData

sealed class ResultState{
    object Loading: ResultState()
    data class Success(val articlesList :List<ArticlesData>): ResultState()
    data class Failure(val errorMessage:String): ResultState()
}