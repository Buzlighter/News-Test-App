package com.test.newsapp.model

import com.google.gson.annotations.SerializedName

data class ResponseNews(@SerializedName("articles")
                        val articleList: List<Article?>)
