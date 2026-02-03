package com.example.gitoutthere.api

import com.google.gson.annotations.SerializedName

data class RepoDto(
    val id: Long,
    val name:String,
    @SerializedName("full_name")
    val fullName: String,
    val owner: OwnerDto,
    val description: String?,
    @SerializedName("html_url")
    val htmlUrl: String,
    val language: String?,
    @SerializedName("stargazers_count")
    val stargazersCount: Int,
    @SerializedName("forks_count")
    val forksCount: Int? = null,
    @SerializedName("open_issues_count")
    val openIssuesCount: Int? = null,
    @SerializedName("updated_at")
    val updatedAt: String? = null
)
