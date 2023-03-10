package com.pascal.newsapp.data.source.local

import com.pascal.newsapp.data.source.local.entity.ArticleEntity
import com.pascal.newsapp.data.source.local.entity.NewsEntity
import com.pascal.newsapp.data.source.local.room.NewsDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalDataSource @Inject constructor(
    private val newsDao: NewsDao
) {
    fun getNews(): Flow<List<NewsEntity>> = newsDao.getNews()

    fun getArticles(name: String): Flow<List<ArticleEntity>> = newsDao.getArticles(name)

    fun getFavoriteArticles(): Flow<List<ArticleEntity>> = newsDao.getFavoriteArticles()

    suspend fun insertNews(newsList: List<NewsEntity>) = newsDao.insertNews(newsList)

    suspend fun insertArticles(articleList: List<ArticleEntity>) =
        newsDao.insertArticles(articleList)

    fun setFavoriteArticle(articleEntity: ArticleEntity, newState: Boolean) {
        articleEntity.isFavorite = newState
        newsDao.updateFavoriteArticle(articleEntity)
    }
}