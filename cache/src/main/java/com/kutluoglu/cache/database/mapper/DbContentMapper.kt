package com.kutluoglu.cache.database.mapper

import com.kutluoglu.cache.database.dbUtils.DateConverter
import com.kutluoglu.cache.database.entity.ContentEntity
import com.kutluoglu.data.model.DataContent
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

/**
 * Map a List of [ContentEntity] instance to and from a List of [DataContent] instance when data is moving between
 * this layer and the Data layer
 */

open class DbContentMapper @Inject constructor() : DbMapper<List<DataContent>, List<ContentEntity>>{
    override fun mapToCached(type: List<DataContent>): List<ContentEntity> {
        val entityList = mutableListOf<ContentEntity>()
        type.map {
            val date = DateConverter().toDateFromString(it.date)
            entityList.add(ContentEntity(it.id, it.title, it.subtitle, date))
        }
        return entityList
    }

    override fun mapFromCached(type: List<ContentEntity>): List<DataContent> {
        val dataList = mutableListOf<DataContent>()
        type.map {
            val date = DateConverter().toDateStringFromLong(it.date.time)
            dataList.add(DataContent(it.id, it.title, it.subTitle, date))
        }
        return dataList
    }
}