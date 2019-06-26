package com.kutluoglu.data

import com.kutluoglu.data.mapper.ContentDetailMapper
import com.kutluoglu.data.mapper.ContentMapper
import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.source.DataStoreFactory
import com.kutluoglu.domain.model.PulseContent
import com.kutluoglu.domain.model.PulseContentDetail
import com.kutluoglu.domain.repository.PulseLiveRepository
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-24.
 *
 */

/**
 * Provides an implementation of the [PulseLiveRepository] interface for communicating to and from
 * data sources
 */

class DataRepository @Inject constructor(
    private val factory: DataStoreFactory,
    private val contentMapper: ContentMapper,
    private val contentDetailMapper: ContentDetailMapper
) : PulseLiveRepository {
    override fun getContentList(): Single<List<PulseContent>> {
        return factory.retreiveCacheDataStore().isCached()
            .flatMap {
                factory.retreiveDataStore(it).getContents()
            }
            .flatMap {
                saveDataContent(it)
                Single.just(
                    contentMapper.mapFromEntityToDomainModel(it)
                )
            }
    }

    private fun saveDataContent(list: List<DataContent>) {
        factory.retreiveCacheDataStore().saveContents(list)
    }

    override fun getContentDetailBy(id: String?): Single<PulseContentDetail> {
        return factory.retreiveCacheDataStore().isDetailCached(id)
            .flatMap {
                factory.retreiveDataStore(it).getContentDetailById(id)
            }
            .flatMap {
                saveDetail(it)
                Single.just(
                    contentDetailMapper.mapFromEntityToDomainModel(it)
                )
            }
    }

    private fun saveDetail(detail: DataContentDetail) {
        factory.retreiveCacheDataStore().addContentDetail(detail)
    }
}