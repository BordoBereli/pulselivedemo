package com.kutluoglu.presentation.mapper

import com.kutluoglu.domain.model.PulseContent
import com.kutluoglu.presentation.model.ContentView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

open class ContentViewMapper @Inject constructor() : ViewMapper<List<PulseContent>, List<ContentView>>{
    override fun mapToView(type: List<PulseContent>): List<ContentView> {
        val viewList = mutableListOf<ContentView>()
        type.map {
            viewList.add(ContentView(it.id, it.title, it.subtitle, it.date))
        }
        return viewList
    }

    override fun mapFromView(type: List<ContentView>): List<PulseContent> {
        val domainList = mutableListOf<PulseContent>()
        type.map {
            domainList.add(PulseContent(it.id, it.title, it.subtitle, it.date))
        }
        return domainList
    }
}