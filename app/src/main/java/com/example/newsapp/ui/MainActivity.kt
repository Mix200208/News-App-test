package com.example.newsapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.R
import com.example.newsapp.rv.NewsAdapter
import com.example.newsapp.server.ServerService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val api: ServerService = Retrofit.Builder().baseUrl("https://newsapi.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ServerService::class.java)

        val adapter = NewsAdapter()

        GlobalScope.launch(Dispatchers.IO) {

            val response = api.news(q = "tesla", from = "2022-02-16", sortBy="publishedAt", apiKey="0d8cbc9442044b3ba54375d27ba30ce4")


            GlobalScope.launch(Dispatchers.Main){
                adapter.setData(response.articles.toMutableList())

            }


        }

        val newsRv = findViewById<RecyclerView>(R.id.newsRV)
        newsRv.layoutManager = LinearLayoutManager(this)
        newsRv.adapter = adapter

    }


}