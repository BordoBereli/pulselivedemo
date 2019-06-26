package com.kutluoglu.pulselivedemo.main.features.detail

import com.kutluoglu.presentation.model.DetailView
import com.kutluoglu.pulselivedemo.base.ViewContract

/**
 * Created by F.K. on 2019-06-25.
 *
 */

interface DetailContract : ViewContract {
    fun initializeUI(detailView: DetailView)
}