package com.zevo.newsappdemo.api

import com.zevo.newsappdemo.models.NewsResponse
import retrofit2.Response
import retrofit2.http.*

interface NewsAPI {


    @GET("v2/top-headlines?country=us&apiKey=e57334091c524e0cbdb35d2ff18a4344")
    suspend fun getNews(): Response<NewsResponse>


}