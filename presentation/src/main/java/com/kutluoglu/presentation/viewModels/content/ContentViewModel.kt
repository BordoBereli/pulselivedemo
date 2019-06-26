package com.kutluoglu.presentation.viewModels.content

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.kutluoglu.domain.interactor.GetContentsUseCase
import com.kutluoglu.presentation.mapper.ContentViewMapper
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.presentation.resource.Resource
import com.kutluoglu.presentation.resource.ResourceState
import javax.inject.Inject

/**
 * Created by F.K. on 2019-06-25.
 *
 */

class ContentViewModel @Inject constructor(
    private val getContentsUseCase: GetContentsUseCase,
    private val mapper: ContentViewMapper
) : ViewModel(), ContentContract {
    override fun getContentLiveData(): LiveData<Resource<List<ContentView>>> {
        return contentLiveData
    }

    override fun loadContents() {
        contentLiveData.postValue(Resource(ResourceState.LOADING, null, null))
        return getContentsUseCase.execute(ContentSubscriber(mapper))
    }

    override fun onCleared() {
        super.onCleared()
        getContentsUseCase.dispose()
    }
}