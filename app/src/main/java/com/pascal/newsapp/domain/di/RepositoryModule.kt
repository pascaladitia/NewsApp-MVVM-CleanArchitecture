package com.pascal.newsapp.domain.di

import com.pascal.newsapp.domain.usecase.NewsInteractor
import com.pascal.newsapp.domain.usecase.NewsUseCase
import com.pascal.newsapp.data.source.NewsRepositoryImpl
import com.pascal.newsapp.data.source.local.LocalDataSource
import com.pascal.newsapp.data.source.remote.RemoteDataSource
import com.pascal.newsapp.domain.repository.NewsRepository
import com.pascal.newsapp.utils.AppExecutors
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepository(
        localDataSource: LocalDataSource,
        remoteDataSource: RemoteDataSource,
        appExecutors: AppExecutors
    ): NewsRepository {
        return NewsRepositoryImpl(
            localDataSource = localDataSource,
            remoteDataSource = remoteDataSource,
            appExecutors = appExecutors
        )
    }

    @Provides
    @Singleton
    fun provideUseCase(repository: NewsRepository): NewsUseCase = NewsInteractor(repository)
}