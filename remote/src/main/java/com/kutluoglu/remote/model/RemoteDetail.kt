package com.kutluoglu.remote.model

import com.google.gson.annotations.SerializedName

data class RemoteDetail(
    @SerializedName("id")
    val id: Int, // 36
    @SerializedName("title")
    val title: String, // Article 8
    @SerializedName("subtitle")
    val subtitle: String, // Article 8 subtitle with placeholder text
    @SerializedName("body")
    val body: String, // Article 8 Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Article 8
    @SerializedName("date")
    val date: String // 18/04/2013 11:48
)