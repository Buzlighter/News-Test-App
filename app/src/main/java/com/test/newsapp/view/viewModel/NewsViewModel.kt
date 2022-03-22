package com.test.newsapp.view.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.newsapp.util.Access
import com.test.newsapp.api.NewsApi
import com.test.newsapp.model.ResponseNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    val newsLiveData = MutableLiveData<ResponseNews>()

    fun getNewsInfo(newsApi: NewsApi?) {
        viewModelScope.launch(Dispatchers.IO) {
            newsApi?.let {
                newsLiveData.postValue(it.getNewsInfo(Access.SOURCE_NAME))
            }
        }
    }
}