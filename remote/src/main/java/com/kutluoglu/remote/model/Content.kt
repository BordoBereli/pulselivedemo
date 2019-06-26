package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class Content(
    @SerializedName("id")
    val id: Int, // 29
    @SerializedName("title")
    val title: String, // Article 1
    @SerializedName("subtitle")
    val subtitle: String, // Article 1 subtitle with placeholder text
    @SerializedName("date")
    val date: String // 11/04/2013 11:48
)