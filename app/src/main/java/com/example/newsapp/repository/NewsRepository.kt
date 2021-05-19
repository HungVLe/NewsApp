package com.example.newsapp.repository

import com.example.newsapp.database.ArticleDB
import com.example.newsapp.models.Article
import com.example.newsapp.service.RetrofitService


class NewsRepository(
    val db: ArticleDB
) {
    suspend fun getBreakingNews(countryCode: String, pageNumber: Int) =
        RetrofitService.newsApi.getBreakingNews(countryCode, pageNumber)

    suspend fun searchNews(searchQuery: String, pageNumber: Int) =
        RetrofitService.newsApi.searchForNews(searchQuery, pageNumber)

    suspend fun insert(article: Article) = db.getArticleDao().insert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}