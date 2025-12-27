package com.greedygame.brokenandroidcomposeproject.domain.di

import com.greedygame.brokenandroidcomposeproject.domain.usecase.ArticlesImpl
import com.greedygame.brokenandroidcomposeproject.domain.usecase.ArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindArticles(impl: ArticlesImpl): ArticlesRepository
}