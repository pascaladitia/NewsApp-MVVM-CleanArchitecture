package com.pascal.newsapp.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pascal.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    newsUseCase: NewsUseCase
) : ViewModel() {

    val getFavoriteArticles = newsUseCase.getFavoriteArticles().asLiveData()
}