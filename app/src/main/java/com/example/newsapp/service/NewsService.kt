package com.example.newsapp.service


import com.example.newsapp.models.NewsResponse
import com.example.newsapp.vo.Constants.Companion.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    suspend fun getBreakingNews(
        @Query("country")
        countryCode: String = "us",
        @Query("pageSize")
        pageSize : Int = 100,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>

    @GET("v2/everything")
    suspend fun searchForNews(
        @Query("q")
        searchQuery: String,
        @Query("apiKey")
        apiKey: String = API_KEY
    ): Response<NewsResponse>
}