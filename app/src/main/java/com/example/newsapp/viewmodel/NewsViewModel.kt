package com.example.newsapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.models.Article

import com.example.newsapp.models.NewsResponse
import com.example.newsapp.repository.NewsRepository
import com.example.newsapp.vo.Constants.Companion.TAB_LIST
import com.example.newsapp.vo.Resource
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Thread.sleep

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {

    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var tabNews: MutableList<MutableLiveData<Resource<NewsResponse>>> = MutableList(TAB_LIST.size){MutableLiveData<Resource<NewsResponse>>()}

    init {
        getBreakingNews("us")
        getTabNews()
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        breakingNews.postValue(Resource.Loading())
        var response = newsRepository.getBreakingNews(countryCode)
        breakingNews.postValue(handleNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        var response = newsRepository.searchNews(searchQuery)
        searchNews.postValue(handleNewsResponse(response))
    }

    fun getTabNews() = viewModelScope.launch {
        TAB_LIST.forEachIndexed { index, s ->
            if (index < 7)
                getCategoryTabNews(s, index)
            else
                getSourceTabNews(s, index)
        }
    }

    fun getCategoryTabNews(category: String, position: Int) = viewModelScope.launch {
        tabNews[position].postValue(Resource.Loading())
        var response = newsRepository.getCategoryTabNews(category)
        tabNews[position].postValue(handleNewsResponse(response))
    }

    fun getSourceTabNews(sources: String, position: Int) = viewModelScope.launch {
        tabNews[position].postValue(Resource.Loading())
        var response = newsRepository.getSourceTabNews(sources)
        tabNews[position].postValue(handleNewsResponse(response))
    }

    private fun handleNewsResponse(response: Response<NewsResponse>): Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.insert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}