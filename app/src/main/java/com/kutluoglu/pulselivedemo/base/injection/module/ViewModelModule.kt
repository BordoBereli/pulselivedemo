package com.kutluoglu.pulselivedemo.base.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kutluoglu.presentation.viewModels.PulseLiveViewModelFactory
import com.kutluoglu.presentation.viewModels.content.ContentViewModel
import com.kutluoglu.presentation.viewModels.detail.ContentDetailViewModel
import com.kutluoglu.pulselivedemo.base.injection.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

/**
 * Created by F.K. on 2019-06-25
 *
 */

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(factory: PulseLiveViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(ContentViewModel::class)
    abstract fun bindContentViewModel(catalogViewModel: ContentViewModel) : ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ContentDetailViewModel::class)
    abstract fun bindContentDetailViewModel(cartViewModel: ContentDetailViewModel) : ViewModel


}