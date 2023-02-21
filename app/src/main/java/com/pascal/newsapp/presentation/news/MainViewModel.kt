package com.pascal.newsapp.presentation.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.pascal.newsapp.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    newsUseCase: NewsUseCase
) : ViewModel() {

    val news = newsUseCase.getAllNews().asLiveData()
}