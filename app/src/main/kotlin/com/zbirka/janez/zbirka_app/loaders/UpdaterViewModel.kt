package com.zbirka.janez.zbirka_app.loaders

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

class UpdaterViewModel : ViewModel() {

    var sharableString = MutableLiveData<String>()

    fun setSharableString(text: String) {
        sharableString.value = text
    }
}