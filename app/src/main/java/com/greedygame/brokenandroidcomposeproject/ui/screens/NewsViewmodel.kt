package com.greedygame.brokenandroidcomposeproject.ui.screens

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.greedygame.brokenandroidcomposeproject.core.utils.ResultState
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesData
import com.greedygame.brokenandroidcomposeproject.domain.model.ArticlesRequest
import com.greedygame.brokenandroidcomposeproject.domain.usecase.ArticlesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewmodel @Inject constructor(private val articlesRepository: ArticlesRepository) :
    ViewModel() {
    private val _uiState = MutableStateFlow<ResultState>(ResultState.Loading)
    val uiState: StateFlow<ResultState> = _uiState

    fun getArticles() {
        viewModelScope.launch {
            articlesRepository.getArticles(ArticlesRequest("android"))
                .onSuccess { data ->
                    _uiState.value = ResultState.Success(  data.articles)
                }
                .onFailure { error ->
                    _uiState.value = ResultState.Failure(error.message.toString())
                }
        }
    }
}