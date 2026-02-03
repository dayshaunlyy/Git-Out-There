package com.example.gitoutthere.api


class GitHubRepository (private val api : GitHubApi = RetrofitClient.api){
    suspend fun searchRepos(q: String) = api.searchRepos(q)

}