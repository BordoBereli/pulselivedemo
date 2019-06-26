package com.kutluoglu.presentation.viewModels.detail

import androidx.lifecycle.LiveData
import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.presentation.resource.Resource

/**
 * Created by F.K. on 2019-06-25.
 *
 */

interface DetailContract {
    fun getContentDetailLiveData() : LiveData<Resource<DetailView>>
    fun getContentDetailById(id: String)
}