package com.example.gitoutthere.api

import retrofit2.http.Query
import retrofit2.http.GET

interface GitHubApi {

    @GET("search/repositories")
    suspend fun searchRepos(
        @Query("q") q: String,
        @Query("sort") sort: String? = null,
        @Query("order") order: String? = null,
        @Query("per_page") perPage: Int = 30,
        @Query("page") page: Int = 1
        ): RepoSearchResponse
}