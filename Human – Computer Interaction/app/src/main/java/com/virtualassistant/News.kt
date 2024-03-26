package com.virtualassistant

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.virtualassistant.databinding.NewsRecycleviewBinding

class News : AppCompatActivity(), NewsItemClicked {

    private lateinit var binding: NewsRecycleviewBinding
    private lateinit var mAdapter: NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using the generated binding class
        binding = NewsRecycleviewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the layout manager for the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        // Fetch news data from the API and set up the adapter
        fetchData()
        mAdapter = NewsAdapter(this)
        binding.recyclerView.adapter = mAdapter
    }

    private fun fetchData() {
        // URL for fetching news data from the API
        val url = "https://newsapi.org/v2/top-headlines?country=gr&apiKey=b7f92dbc2716423e8ba6337dca31f2a8"

        // Create a JsonObjectRequest to fetch the news data
        val jsonObjectRequest = object : JsonObjectRequest(
            Method.GET, url, null,
            { response ->
                // Process the JSON response and extract news articles
                val newsJsonArray = response.getJSONArray("articles")
                val newsArray = ArrayList<NewsDataModel>()

                for (i in 0 until newsJsonArray.length()) {
                    val newsJsonObject = newsJsonArray.getJSONObject(i)
                    val news = NewsDataModel(
                        newsJsonObject.getString("title"),
                        newsJsonObject.getString("author"),
                        newsJsonObject.getString("url"),
                        newsJsonObject.getString("urlToImage")
                    )
                    newsArray.add(news)
                }
                // Update the adapter with the fetched news data
                mAdapter.updateNews(newsArray)
            },
            { error ->

            }
        ) {
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0" // Set the User-Agent header for the HTTP request
                return headers
            }
        }

        // Add the request to the request queue using a custom singleton class
        NewsMySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun onItemClicked(item: NewsDataModel) {
        // Create a CustomTabsIntent and launch a custom tab with the news article URL
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(item.url))
    }
}

// Data class representing a news article
data class NewsDataModel(
    val title: String,
    val author: String,
    val url: String,
    val imageUrl: String,
)
