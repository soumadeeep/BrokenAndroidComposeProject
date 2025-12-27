package com.greedygame.brokenandroidcomposeproject.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.greedygame.brokenandroidcomposeproject.BuildConfig

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {


    /** Ok http client **/
    @Provides
    @Singleton
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    /** Retrofit **/
    @Provides
    @Singleton
    fun Retrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient).build()
    }
}