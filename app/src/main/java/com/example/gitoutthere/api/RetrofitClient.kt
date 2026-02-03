package com.example.gitoutthere.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.example.gitoutthere.api.GitHubApi

object RetrofitClient {
    private const val BASE_URL = "https://api.github.com/"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api : GitHubApi by lazy{
        retrofit.create(GitHubApi::class.java)
    }
}