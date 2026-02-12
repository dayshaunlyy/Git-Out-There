package com.example.gitoutthere.api

import com.google.gson.annotations.SerializedName

data class IssueDto(
    val id: Long,
    val number: Int,
    @SerializedName("html_url")
    val htmlUrl: String,
    val title: String,
    val body: String,
    val labels: List<LabelDto>
)
