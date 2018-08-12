package com.zbirka.janez.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.zbirka.janez.BR

class SearchItem : BaseObservable() {

    @Bindable
    var searchString: String = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.searchString)
        }
        get() = field

}