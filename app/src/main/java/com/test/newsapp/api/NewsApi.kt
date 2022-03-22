package com.test.newsapp.api

import com.test.newsapp.model.ResponseNews
import com.test.newsapp.util.Access
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET(Access.NEWS_SERVER_PATH_HEADLINES)
    suspend fun getNewsInfo(
        @Query("sources") source: String
    ) : ResponseNews
}