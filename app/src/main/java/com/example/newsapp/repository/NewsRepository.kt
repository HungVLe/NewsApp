package com.example.newsapp.repository

import com.example.newsapp.database.ArticleDB
import com.example.newsapp.models.Article
import com.example.newsapp.service.RetrofitService


class NewsRepository(
    val db: ArticleDB
) {
    suspend fun getBreakingNews(countryCode: String) =
        RetrofitService.newsApi.getBreakingNews(countryCode)

    suspend fun searchNews(searchQuery: String) =
        RetrofitService.newsApi.searchForNews(searchQuery)

    suspend fun getCategoryTabNews(category: String) =
        RetrofitService.newsApi.getCategoryTabNews(category)

    suspend fun getSourceTabNews(sources: String) =
        RetrofitService.newsApi.getSourceTabNews(sources)

    suspend fun insert(article: Article) = db.getArticleDao().insert(article)

    fun getSavedNews() = db.getArticleDao().getAllArticles()

    suspend fun deleteArticle(article: Article) = db.getArticleDao().deleteArticle(article)
}