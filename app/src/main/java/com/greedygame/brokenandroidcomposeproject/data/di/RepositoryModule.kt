package com.greedygame.brokenandroidcomposeproject.data.di

import com.greedygame.brokenandroidcomposeproject.data.repository.GetArticlesImpl
import com.greedygame.brokenandroidcomposeproject.data.repository.GetArticlesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
@Binds
@Singleton
abstract fun bindArticlesDetails(impl: GetArticlesImpl): GetArticlesRepository
}