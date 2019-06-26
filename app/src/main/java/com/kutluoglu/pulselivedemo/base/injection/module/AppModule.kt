package com.kutluoglu.pulselivedemo.base.injection.module

import android.app.Application
import android.content.Context
import com.kutluoglu.cache.PulseLiveCacheImp
import com.kutluoglu.cache.PulseLiveSharedPreference
import com.kutluoglu.cache.database.db.PulseLiveDemoDb
import com.kutluoglu.cache.database.mapper.DbContentDetailMapper
import com.kutluoglu.cache.database.mapper.DbContentMapper
import com.kutluoglu.data.DataRepository
import com.kutluoglu.data.executor.JobExecutor
import com.kutluoglu.data.mapper.ContentDetailMapper
import com.kutluoglu.data.mapper.ContentMapper
import com.kutluoglu.data.repository.Cache
import com.kutluoglu.data.repository.Remote
import com.kutluoglu.data.source.DataStoreFactory
import com.kutluoglu.domain.executor.PostExecutionThread
import com.kutluoglu.domain.executor.ThreadExecutor
import com.kutluoglu.domain.repository.PulseLiveRepository
import com.kutluoglu.pulselivedemo.BuildConfig
import com.kutluoglu.pulselivedemo.UiThread
import com.kutluoglu.pulselivedemo.base.injection.scopes.PerApplication
import com.kutluoglu.pulselivedemo.utils.NetworkUtils
import com.kutluoglu.remote.PulseLiveDemoApi
import com.kutluoglu.remote.PulseLiveRemoteImp
import com.kutluoglu.remote.ServiceFactory
import dagger.Module
import dagger.Provides

/**
 * Created by F.K. on 2019-06-25.
 *
 */

@Module(includes = [ViewModelModule::class, MainActivityModule::class])
class AppModule {
    @Provides
    @PerApplication
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Provides
    @PerApplication
    internal fun provideThreadExecutor(jobExecutor: JobExecutor) : ThreadExecutor {
        return jobExecutor
    }

    @Provides
    @PerApplication
    internal fun providePostExecutionThread(uiThread: UiThread): PostExecutionThread {
        return uiThread
    }

    @Provides
    @PerApplication
    internal fun provideSharedPreferences(context: Context): PulseLiveSharedPreference {
        return PulseLiveSharedPreference(context)
    }


    @Provides
    @PerApplication
    internal fun providePulseLiveRepository(factory: DataStoreFactory,
                                            contentMapper: ContentMapper,
                                            contentDetailMapper: ContentDetailMapper
    ) : PulseLiveRepository {
        return DataRepository(factory, contentMapper, contentDetailMapper)
    }

    @Provides
    @PerApplication
    internal fun providePulseLiveCache(pulseLiveDb: PulseLiveDemoDb,
                                       plSharedPreference: PulseLiveSharedPreference,
                                       dbContentMapper: DbContentMapper,
                                       dbContentDetailMapper: DbContentDetailMapper
    ) : Cache {
        return PulseLiveCacheImp(pulseLiveDb, plSharedPreference, dbContentMapper, dbContentDetailMapper)
    }

    @Provides
    @PerApplication
    internal fun providePLService(): PulseLiveDemoApi {
        return ServiceFactory.getPulseLiveDemoApi(BuildConfig.DEBUG)
    }

    @Provides
    @PerApplication
    internal fun providePulseLiveRemote(service: PulseLiveDemoApi,
                                        contentMapper: com.kutluoglu.remote.mapper.ContentMapper,
                                        contentDetailMapper: com.kutluoglu.remote.mapper.ContentDetailMapper
    ): Remote {
        return PulseLiveRemoteImp(service, contentMapper, contentDetailMapper)
    }

    @Provides
    @PerApplication
    internal fun providePulseLiveDatabase(context: Context): PulseLiveDemoDb {
        return PulseLiveDemoDb.getInsatnce(context)
    }

    @Provides
    @PerApplication
    internal fun provideNetworkUtils(context: Context) : NetworkUtils {
        return NetworkUtils(context)
    }

}