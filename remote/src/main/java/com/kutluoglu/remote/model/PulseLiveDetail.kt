package com.kutluoglu.remote.model


import com.google.gson.annotations.SerializedName

data class PulseLiveDetail(
    @SerializedName("item")
    val item: RemoteDetail
)