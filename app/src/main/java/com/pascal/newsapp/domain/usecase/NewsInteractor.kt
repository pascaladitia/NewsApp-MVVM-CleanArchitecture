package com.pascal.newsapp.domain.usecase

import com.pascal.newsapp.data.source.Resource
import com.pascal.newsapp.domain.model.Article
import com.pascal.newsapp.domain.model.News
import com.pascal.newsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NewsInteractor @Inject constructor(
    private val newsRepository: NewsRepository
) : NewsUseCase {
    override fun getAllNews(): Flow<Resource<List<News>>> {
        return newsRepository.getAllNews()
    }

    override fun getAllArticles(id: String, name: String): Flow<Resource<List<Article>>> {
        return newsRepository.getAllArticle(id, name)
    }

    override fun getFavoriteArticles(): Flow<List<Article>> {
        return newsRepository.getFavoriteArticles()
    }

    override fun setFavoriteArticle(article: Article, state: Boolean) {
        return newsRepository.setFavoriteArticle(article, state)
    }
}