package com.kutluoglu.cache.database.mapper

import com.kutluoglu.cache.database.dbUtils.DateConverter
import com.kutluoglu.cache.database.entity.ContentDetailEntity
import com.kutluoglu.data.model.DataContentDetail
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

/**
 * Map a [DataContentDetail] instance to and from a [ContentDetailEntity] instance when data is moving between
 * database and the Cache layer
 */

open class DbContentDetailMapper @Inject constructor() : DbMapper<DataContentDetail, ContentDetailEntity>{
    override fun mapToCached(type: DataContentDetail): ContentDetailEntity {
        val date = DateConverter().toDateFromString(type.date)
        return ContentDetailEntity(type.id, type.title, type.subtitle, type.body, date)
    }

    override fun mapFromCached(type: ContentDetailEntity): DataContentDetail {
        val date = DateConverter().toDateStringFromLong(type.date.time)
        return DataContentDetail(type.contentId, type.title, type.subTitle, type.body, date)
    }
}