package com.zbirka.janez.application

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.zbirka.janez.common.AppCoroutineContext
import com.zbirka.janez.common.MyDateTypeAdapter
import com.zbirka.janez.network.DataService
import com.zbirka.janez.repository.DataRepository
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import javax.inject.Singleton

@Module
class UtilsModule {

    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        return GsonBuilder().registerTypeAdapter(Date::class.java, MyDateTypeAdapter()).create()
    }

    @Provides
    @Singleton
    internal fun provideRetrofit(gson: Gson): Retrofit {
        return Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Provides
    @Singleton
    internal fun provideWeatherService(retrofit: Retrofit): DataService {
        return retrofit.create(DataService::class.java)
    }

    @Provides
    internal fun provideWeatherRepository(service: DataService, appCoroutineContext: AppCoroutineContext): DataRepository {
        return DataRepository(service, appCoroutineContext)
    }
}