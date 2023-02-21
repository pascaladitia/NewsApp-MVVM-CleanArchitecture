package com.pascal.newsapp.domain.usecase

import com.pascal.newsapp.data.source.Resource
import com.pascal.newsapp.domain.model.Article
import com.pascal.newsapp.domain.model.News
import kotlinx.coroutines.flow.Flow

interface NewsUseCase {
    fun getAllNews(): Flow<Resource<List<News>>>
    fun getAllArticles(id: String, name: String): Flow<Resource<List<Article>>>
    fun getFavoriteArticles(): Flow<List<Article>>
    fun setFavoriteArticle(article: Article, state: Boolean)
}