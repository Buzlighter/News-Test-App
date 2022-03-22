package com.test.newsapp.model

import com.google.gson.annotations.SerializedName

data class Article(
    @SerializedName("source")
    val source: Source?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("url")
    val url: String?,
    @SerializedName("urlToImage")
    val image: String?,
    @SerializedName("publishedAt")
    val publishedDate: String?,
)
