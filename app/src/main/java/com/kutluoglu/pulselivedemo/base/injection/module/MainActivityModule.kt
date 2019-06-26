package com.kutluoglu.pulselivedemo.base.injection.module

import com.kutluoglu.pulselivedemo.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Module used to provide dependencies at an activity-level.
 *
 */

@Module
abstract class MainActivityModule {

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributeMainActivity() : MainActivity
}
