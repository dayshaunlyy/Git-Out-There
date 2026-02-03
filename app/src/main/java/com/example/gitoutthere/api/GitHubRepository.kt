package com.example.gitoutthere.api

import com.example.gitoutthere.api.GitHubApi


class GitHubRepository (private val api : GitHubApi = RetrofitClient.api){
    suspend fun searchRepos(q: String) = api.searchRepos(q)

}