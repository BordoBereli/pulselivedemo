package com.kutluoglu.remote.mapper

import com.kutluoglu.data.model.DataContentDetail
import com.kutluoglu.remote.model.PulseLiveDetail
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

open class ContentDetailMapper @Inject constructor() : EntityMapper<PulseLiveDetail, DataContentDetail>{
    override fun mapFromRemote(type: PulseLiveDetail): DataContentDetail {
        val detail = type.item
        return DataContentDetail(detail.id, detail.title, detail.subtitle, detail.body, detail.date)
    }
}