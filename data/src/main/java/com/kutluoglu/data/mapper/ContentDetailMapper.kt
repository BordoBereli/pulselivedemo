package com.kutluoglu.data.mapper

import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.domain.model.PulseContentDetail
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-24.
 *
 */

open class ContentDetailMapper @Inject constructor() : Mapper<DataContentDetail, PulseContentDetail>{
    override fun mapFromEntityToDomainModel(type: DataContentDetail): PulseContentDetail {
        return PulseContentDetail(type.id, type.title, type.subtitle, type.body, type.date)
    }

    override fun mapToEntityFromDomainModel(type: PulseContentDetail): DataContentDetail {
        return DataContentDetail(type.id, type.title, type.subtitle, type.body, type.date)
    }
}