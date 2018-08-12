package com.zbirka.janez.application

import android.content.Context
import com.zbirka.janez.common.AppCoroutineContext
import com.zbirka.janez.location.CurrentLocationListener
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [UtilsModule::class])
class ZbirkaApplicationModule(val application: ZbirkaApplication) {

    @Provides
    @Singleton
    internal fun provideApplication(): ZbirkaApplication = application

    @Provides
    @Singleton
    internal fun provideApplicationContext(): Context = application

    @Provides
    @Singleton
    internal fun provideCurrentLocationListener(context: Context): CurrentLocationListener = CurrentLocationListener(context)

    @Provides
    @Singleton
    internal fun provideAppCorutineContext(): AppCoroutineContext = AppCoroutineContext()

}
