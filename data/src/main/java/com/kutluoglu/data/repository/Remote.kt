package com.kutluoglu.data.repository

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-24.
 *
 */
interface Remote {
    /**
     * Retrieve a [List] of [DataContent] from the remote API Service
     */
    fun getContents(): Single<List<DataContent>>

    /**
     * Retrieve a [DataContentDetail] by its id.
     */
    fun getContentDetailById(id: String?): Single<DataContentDetail>
}