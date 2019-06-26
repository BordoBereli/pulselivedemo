package com.kutluoglu.pulselivedemo.base.injection.component

import android.app.Application
import com.kutluoglu.pulselivedemo.PulseLiveApp
import com.kutluoglu.pulselivedemo.base.injection.module.AppModule
import com.kutluoglu.pulselivedemo.base.injection.module.MainActivityModule
import com.kutluoglu.pulselivedemo.base.injection.scopes.PerApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

/**
 * Created by F.K. on 2019-06-25
 *
 */

@PerApplication
@Component(
    modules = [
        AndroidSupportInjectionModule::class, // Because of using AndroidX fragment
        AppModule::class,
        MainActivityModule::class
    ]
)

interface AppComponent {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application) : Builder

        fun build() : AppComponent
    }

    fun inject(plApp: PulseLiveApp)
}