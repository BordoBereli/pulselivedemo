package com.kutluoglu.data.mapper

import com.kutluoglu.data.model.DataContent
import com.kutluoglu.domain.model.PulseContent
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-24.
 *
 */

open class ContentMapper @Inject constructor() : Mapper<List<DataContent>, List<PulseContent>> {
    override fun mapFromEntityToDomainModel(type: List<DataContent>): List<PulseContent> {
        val domainList = mutableListOf<PulseContent>()
        type.map {
            domainList.add(
                PulseContent(it.id, it.title, it.subtitle, it.date)
            )
        }
        return domainList
    }

    override fun mapToEntityFromDomainModel(type: List<PulseContent>): List<DataContent> {
        val dataList = mutableListOf<DataContent>()
        type.map {
            dataList.add(
                DataContent(it.id, it.title, it.subtitle, it.date)
            )
        }
        return dataList
    }
}