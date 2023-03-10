package com.pascal.newsapp.data.source.remote

import com.pascal.newsapp.data.source.remote.network.ApiResponse
import com.pascal.newsapp.data.source.remote.network.ApiService
import com.pascal.newsapp.data.source.remote.response.MainModel
import com.pascal.newsapp.data.source.remote.response.NewsModel
import com.pascal.newsapp.utils.Constant.API_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {
    fun getAllNews(): Flow<ApiResponse<MainModel>> {
        return flow {
            try {
                val response = apiService.getNews(API_KEY)
                val data = response.sourceList
                if (data.isNotEmpty()) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }

    fun getArticles(id: String): Flow<ApiResponse<NewsModel>> {
        return flow {
            try {
                val response = apiService.getArticles(id, API_KEY)
                val data = response.articles
                if (data.isNotEmpty()) emit(ApiResponse.Success(response))
                else emit(ApiResponse.Empty)
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}