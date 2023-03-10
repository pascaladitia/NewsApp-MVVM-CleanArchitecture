package com.pascal.newsapp.data.di

import android.app.Application
import androidx.room.Room
import com.pascal.newsapp.data.source.local.room.NewsDao
import com.pascal.newsapp.data.source.local.room.NewsDatabase
import com.pascal.newsapp.utils.Constant.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideNoteDatabase(application: Application) = Room.databaseBuilder(
        application,
        NewsDatabase::class.java,
        DATABASE_NAME
    ).build()

    @Provides
    @Singleton
    fun provideDao(database: NewsDatabase): NewsDao {
        return database.newsDao()
    }
}