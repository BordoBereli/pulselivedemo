package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class PulseLive(
    @SerializedName("items")
    val items: List<Content>
)