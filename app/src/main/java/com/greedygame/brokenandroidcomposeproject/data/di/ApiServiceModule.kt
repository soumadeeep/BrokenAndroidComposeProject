package com.greedygame.brokenandroidcomposeproject.data.di

import com.greedygame.brokenandroidcomposeproject.data.source.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiServiceModule {
   @Provides
   @Singleton
   fun retrofitApiService(retrofit: Retrofit): ApiService{
       return retrofit.create(ApiService::class.java)
   }
}