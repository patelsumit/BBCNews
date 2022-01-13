package com.app.bbcnewsapp.endpoint

import com.app.bbcnewsapp.models.NewsList
import retrofit2.http.GET
import retrofit2.http.Headers

interface NewsService {
    @Headers("Content-Type: application/json")
    @GET("headlines.json")
    suspend fun getHeadlines() : NewsList
}