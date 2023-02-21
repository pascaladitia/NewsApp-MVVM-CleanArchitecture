package com.pascal.newsapp.domain.repository

import com.pascal.newsapp.data.source.Resource
import com.pascal.newsapp.domain.model.Article
import com.pascal.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getAllArticle(id: String, name: String): Flow<Resource<List<Article>>>
    fun getFavoriteArticles(): Flow<List<Article>>
    fun setFavoriteArticle(article: Article, state: Boolean)
}