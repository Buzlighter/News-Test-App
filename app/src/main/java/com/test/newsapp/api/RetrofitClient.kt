package com.test.newsapp.api

import com.test.newsapp.util.Access
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitClient {

    companion object {
        lateinit var newsApi: NewsApi

        fun configureClient() {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val httpClient = OkHttpClient.Builder().
            addInterceptor { chain ->
                val original = chain.request()
                val requestBuilder = original.newBuilder()
                    .header("Authorization", Access.NEWS_API_KEY)

                val request = requestBuilder.build()
                chain.proceed(request)
            }.addNetworkInterceptor(logging).build()

            configureRetrofit(httpClient)
        }

        private fun configureRetrofit(okHttpClient: OkHttpClient) {
            val retrofit = Retrofit.Builder()
                .baseUrl(Access.NEWS_SERVER_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            newsApi = retrofit.create(NewsApi::class.java)
        }
    }
}