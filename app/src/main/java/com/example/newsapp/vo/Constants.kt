package com.example.newsapp.vo

class Constants {
    companion object {
        const val API_KEY = "eb9250ba5c9f4a36bc504b6451a79a9c"
        const val BASE_URL = "https://newsapi.org"
        val TAB_LIST: List<String> = listOf(
            "business",
            "entertainment",
            "general",
            "health",
            "science",
            "sports",
            "technology",
            "bbc-news",
            "cnn",
            "fox-news"
        )
    }
}