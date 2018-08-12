package com.zbirka.janez.application

import android.app.Application

class ZbirkaApplication : Application() {

    companion object {
        lateinit var component: ZbirkaComponent
    }

    override fun onCreate() {
        super.onCreate()

        component = DaggerZbirkaComponent.builder()
                .zbirkaApplicationModule(ZbirkaApplicationModule(this)).build()
        component.inject(this)
    }
}
