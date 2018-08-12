package com.zbirka.janez.application

import com.zbirka.janez.location.CurrentLocationListener
import com.zbirka.janez.repository.DataRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ZbirkaApplicationModule::class)])
interface ZbirkaComponent {

    fun inject(app: ZbirkaApplication)

    fun getDataRepository(): DataRepository

    fun getCurrentLocationListener(): CurrentLocationListener
}