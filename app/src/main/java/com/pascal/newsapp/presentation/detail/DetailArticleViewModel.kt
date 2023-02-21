package com.pascal.newsapp.presentation.detail

import androidx.lifecycle.ViewModel
import com.pascal.newsapp.domain.model.Article
import com.pascal.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailArticleViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    fun setFavoriteArticle(article: Article, newStatus: Boolean) =
        newsUseCase.setFavoriteArticle(article, newStatus)
}