package com.example.newsapp.server

import android.telecom.Call
import com.example.newsapp.server.ServerService.Companion.link
import data.Article
import data.News
import retrofit2.http.GET
import retrofit2.http.Query

interface ServerService {

    @GET(link)
    suspend fun news(
        @Query("q",) q: String,
        @Query("from") from: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String,
    ): News

    companion object{
        const val link = "v2/everything"
    }
}