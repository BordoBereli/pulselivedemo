package com.kutluoglu.remote

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.repository.Remote
import com.kutluoglu.remote.mapper.ContentDetailMapper
import com.kutluoglu.remote.mapper.ContentMapper
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

/**
 * Remote implementation for retrieving PulseLive Api instances. This class implements the
 * [Remote] from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */

class PulseLiveRemoteImp @Inject constructor(
    private val pulseLiveApi: PulseLiveDemoApi,
    private val contentMapper: ContentMapper,
    private val contentDetailMapper: ContentDetailMapper
) : Remote {
    override fun getContents(): Single<List<DataContent>> {
        return pulseLiveApi.getContents()
            .map {
                contentMapper.mapFromRemote(it)
            }
    }

    override fun getContentDetailById(id: String?): Single<DataContentDetail> {
        return pulseLiveApi.getContentDetailBy(id)
            .map {
                contentDetailMapper.mapFromRemote(it)
            }
    }
}