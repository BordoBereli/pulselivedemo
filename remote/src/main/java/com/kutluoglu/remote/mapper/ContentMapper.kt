package com.kutluoglu.remote.mapper

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.remote.model.PulseLive
import javax.inject.Inject


/**
 * Created by F.K. on 2019-06-25.
 *
 */
open class ContentMapper @Inject constructor() : EntityMapper<PulseLive, List<DataContent>> {
    override fun mapFromRemote(type: PulseLive): List<DataContent> {
        val dataList = mutableListOf<DataContent>()
        type.items.map {
            dataList.add(DataContent(it.id, it.title, it.subtitle, it.date))
        }
        return dataList
    }
}