package com.kutluoglu.pulselivedemo.base.injection.module

import com.kutluoglu.pulselivedemo.main.features.content.Contents
import com.kutluoglu.pulselivedemo.main.features.detail.ContentDetail
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by F.K. on 2019-06-25
 *
 */

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeContentFragment() : Contents

    @ContributesAndroidInjector
    abstract fun contributeDetailFragment() : ContentDetail
 }