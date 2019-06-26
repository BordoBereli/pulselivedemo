package com.kutluoglu.cache

import com.kutluoglu.cache.database.db.PulseLiveDemoDb
import com.kutluoglu.cache.database.mapper.DbContentDetailMapper
import com.kutluoglu.cache.database.mapper.DbContentMapper
import com.kutluoglu.data.model.DataContent
import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.data.repository.Cache
import io.reactivex.Single
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

/**
 *This class implements the [Cache] repository from the Data layer as it is that layers responsibility for defining the
 * operations in which data store implementation layers can carry out.
 */

class PulseLiveCacheImp @Inject constructor(
    private val pulseLiveDB: PulseLiveDemoDb,
    private val pulseLiveSharedPreference: PulseLiveSharedPreference,
    private val dbContentMapper: DbContentMapper,
    private val dbContentDetailMapper: DbContentDetailMapper
) : Cache {
    override fun saveDataContents(contents: List<DataContent>): Single<Boolean> {
        return if (isExpired()) {
            val idList = pulseLiveDB.contentDao().insertAllContents(
                dbContentMapper.mapToCached(contents)
            )
            if (idList.isNotEmpty()) setLastCacheTime()
            Single.just(idList.isNotEmpty())
        } else Single.just(true)
    }

    override fun getContents(): Single<List<DataContent>> {
        return pulseLiveDB.contentDao().getContents()
            .map {
                dbContentMapper.mapFromCached(it)
            }
    }

    override fun saveDataContentDetail(dataContentDetail: DataContentDetail): Single<Boolean> {
        val id = pulseLiveDB.contentDetailDao().insertContentDetail(
            dbContentDetailMapper.mapToCached(dataContentDetail)
        )
        return Single.just(id > 0)
    }

    override fun getDataContentDetailById(id: String?): Single<DataContentDetail> {
        return pulseLiveDB.contentDetailDao().getContentDetailByID(id!!.toInt())
            .map {
                dbContentDetailMapper.mapFromCached(it)
            }
    }

    override fun isCached(): Single<Boolean> {
        return pulseLiveDB.contentDao().getContents().map {
            it.isNotEmpty()
        }
    }

    override fun isDetailCached(id: String?): Single<Boolean> {
        return pulseLiveDB.contentDetailDao().getContentDetailByID(id!!.toInt()).map {
            true
        }.onErrorReturn {
            false
        }
    }

    override fun setLastCacheTime() {
        pulseLiveSharedPreference.lastCacheTime = System.currentTimeMillis()
    }

    override fun isExpired(): Boolean {
        val currentTime = System.currentTimeMillis()
        val lastUpdateTime = pulseLiveSharedPreference.lastCacheTime
        return currentTime - lastUpdateTime > EXPIRATION_TIME

    }

    companion object {
        private const val EXPIRATION_TIME = (5 * 60 * 1000).toLong() // 5 Minutes for Demo purpose
    }
}