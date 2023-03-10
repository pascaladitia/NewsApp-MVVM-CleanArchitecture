package com.pascal.newsapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    val id: Int = 0,
    val name: String,
    val author: String?,
    val title: String?,
    val desc: String?,
    val url: String?,
    val urlToImage: String?,
    val publishedAt: String?,
    val isFavorite: Boolean = false
) : Parcelable
