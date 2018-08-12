package com.zbirka.janez.zbirka_app.activity.main

import android.arch.lifecycle.ViewModelProviders
import com.zbirka.janez.application.WorkScope
import com.zbirka.janez.application.ZbirkaComponent
import com.zbirka.janez.zbirka_app.loaders.UpdaterViewModel
import com.zbirka.janez.zbirka_app.loaders.WebDataViewModel
import dagger.Component
import dagger.Module
import dagger.Provides

@Module
class MainModule(val mainActivity: MainActivity) {

    @Provides
    fun provideUpdaterViewModel(): UpdaterViewModel = ViewModelProviders.of(mainActivity)[UpdaterViewModel::class.java]

    @Provides
    fun provideWebDataViewModel(): WebDataViewModel = ViewModelProviders.of(mainActivity)[WebDataViewModel::class.java]

}

@WorkScope
@Component(
        dependencies = [ZbirkaComponent::class],
        modules = [MainModule::class]
)
interface MainComponent {
    fun inject(mainActivity: MainActivity)

//    fun provideWebDataViewModel(): WebDataViewModel
//    fun provideUpdaterViewModel(): UpdaterViewModel
}