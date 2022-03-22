package com.test.newsapp.view

import WebViewFragment
import android.os.Bundle
import androidx.fragment.app.commit
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.test.newsapp.R
import com.test.newsapp.util.Access
import com.test.newsapp.api.RetrofitClient
import com.test.newsapp.model.Article
import com.test.newsapp.view.viewModel.NewsViewModel

class NewsFragment : Fragment(R.layout.fragment_news) {
    private val newsViewModel: NewsViewModel by viewModels()
    private lateinit var newsRecycler: RecyclerView
    private lateinit var newsAdapter: NewsAdapter
    lateinit var newsList: List<Article?>
    val bundle = Bundle()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        newsRecycler = view.findViewById(R.id.news_recycler)
        newsViewModel.getNewsInfo(RetrofitClient.newsApi)

        fitRecyclerView()
    }

    private fun fitRecyclerView() {
        newsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            val dividerItemDecoration = DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL)
            AppCompatResources.getDrawable(requireContext(), R.drawable.line_divider)?.let {
                dividerItemDecoration.setDrawable(it)
            }
            addItemDecoration(dividerItemDecoration)

            newsViewModel.newsLiveData.observe(viewLifecycleOwner) {
                newsList = ArrayList(it.articleList)
                newsAdapter = NewsAdapter(newsList)
                newsRecycler.adapter = newsAdapter
                newsAdapter.setOnItemClickListener(newsListener)
            }
        }
    }

    private val newsListener = object: NewsAdapter.OnItemClickListener {
        override fun onItemClick(article: Article?) {
            bundle.putString(Access.NEWS_BUNDLE_KEY, article?.url)
            parentFragmentManager.commit {
                replace<WebViewFragment>(R.id.fragment_main_container, Access.WEB_VIEW_FRAGMENT, bundle)
                setReorderingAllowed(true)
                addToBackStack(null)
            }
        }
    }
}