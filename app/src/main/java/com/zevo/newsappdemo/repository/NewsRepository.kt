package com.zevo.newsappdemo.repository

import androidx.lifecycle.MutableLiveData
import com.zevo.newsappdemo.api.NewsAPI
import com.zevo.newsappdemo.models.NewsResponse
import com.zevo.newsappdemo.utils.NetworkResult
import org.json.JSONObject
import javax.inject.Inject

class NewsRepository @Inject constructor(private val newsAPI: NewsAPI) {

    private val _newsLiveData = MutableLiveData<NetworkResult<NewsResponse>>()
    val newsLiveData get() = _newsLiveData


    suspend fun getNews() {
        _newsLiveData.postValue(NetworkResult.Loading())
        val response = newsAPI.getNews()
        if (response.isSuccessful && response.body() != null) {
            _newsLiveData.postValue(NetworkResult.Success(response.body()!!))
        } else if (response.errorBody() != null) {
            val errorObj = JSONObject(response.errorBody()!!.charStream().readText())
            _newsLiveData.postValue(NetworkResult.Error(errorObj.getString("message")))
        } else {
            _newsLiveData.postValue(NetworkResult.Error("Something Went Wrong"))
        }
    }
}