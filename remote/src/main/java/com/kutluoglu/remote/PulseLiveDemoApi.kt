package com.kutluoglu.remote

import com.kutluoglu.remote.model.PulseLive
import com.kutluoglu.remote.model.PulseLiveDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * Created by F.K. on 2019-06-25.
 *
 */

/**
 * Defines the abstract methods used for interacting with the PulseLive API
 */

interface PulseLiveDemoApi {
    /**
     * Get Content List
     */
    @GET("contentList.json")
    fun getContents() : Single<PulseLive>

    /**
     * Get Content detail by its id
     */
    @GET("content/{contentID}.json")
    fun getContentDetailBy(@Path("contentID") contentID: String?) : Single<PulseLiveDetail>
}