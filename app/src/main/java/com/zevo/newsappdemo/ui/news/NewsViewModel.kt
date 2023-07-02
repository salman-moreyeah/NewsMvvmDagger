package com.zevo.newsappdemo.ui.news

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.zevo.newsappdemo.repository.NewsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val newsRepository: NewsRepository) : ViewModel() {

    val newsLiveData get() = newsRepository.newsLiveData

    fun getAllNews() {
        viewModelScope.launch {
            newsRepository.getNews()
        }
    }

}