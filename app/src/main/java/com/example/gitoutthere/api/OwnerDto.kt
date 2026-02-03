package com.example.gitoutthere.api

import com.google.gson.annotations.SerializedName
data class OwnerDto(
    val login: String,
    @SerializedName("avatar_url")
    val avatarUrl : String
)
