package com.example.gitoutthere.api

class GitHubRepository (private val api : GitHubApi = RetrofitClient.api){
    suspend fun searchRepos(q: String) = api.searchRepos(q)

    suspend fun getReadme(owner: String, repo: String) = api.getReadme(owner, repo)

    suspend fun getIssues(owner: String, repo: String): List<IssueDto> {
        val issues = api.getIssues(owner, repo)
        println("GitHub Issues Response: Received ${issues.size} issues.")
        issues.forEach { issue ->
            println(issue)
        }
        return issues
    }

}