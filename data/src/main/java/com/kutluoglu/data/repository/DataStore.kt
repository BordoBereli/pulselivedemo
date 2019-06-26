package com.kutluoglu.data.repository

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import io.reactivex.Single

/**
 * Created by F.K. on 2019-06-24.
 *
 */

/**
 * Interface defining methods for the data operations related to PulseLiveDemo.
 * This is to be implemented by external data source layers, setting the requirements for the
 * operations that need to be implemented
 */

interface DataStore {
    fun getContents() : Single<List<DataContent>>
    fun getContentDetailById(id: String?) : Single<DataContentDetail>
    fun saveContents(contents: List<DataContent>) : Single<Boolean>
    fun addContentDetail(contentDetail: DataContentDetail) : Single<Boolean>
    fun isCached() : Single<Boolean>
    fun isDetailCached(id: String?) : Single<Boolean>
}