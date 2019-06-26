package com.kutluoglu.presentation.mapper

import com.kutluoglu.domain.model.PulseContentDetail
import com.kutluoglu.presentation.model.DetailView
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

open class DetailViewMapper @Inject constructor() : ViewMapper<PulseContentDetail, DetailView>{
    override fun mapToView(type: PulseContentDetail): DetailView {
        return DetailView(type.id, type.title, type.subtitle, type.body, type.date)
    }

    override fun mapFromView(type: DetailView): PulseContentDetail {
        return PulseContentDetail(type.id, type.title, type.subtitle, type.body, type.date)
    }
}