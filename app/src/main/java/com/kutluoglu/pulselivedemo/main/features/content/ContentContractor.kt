package com.kutluoglu.pulselivedemo.main.features.content

import android.view.View
import com.kutluoglu.presentation.model.ContentView
import com.kutluoglu.pulselivedemo.base.ViewContract

/**
 * Created by F.K. on 2019-06-25.
 *
 */

interface ContentContractor : ViewContract {
    fun initializeUI()
    fun contentClicked(content: ContentView, view: View)
    fun setContentRv(adapater: ContentRvAdapter)
}