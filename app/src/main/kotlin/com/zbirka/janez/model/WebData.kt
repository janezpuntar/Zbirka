package com.zbirka.janez.model

import android.databinding.BaseObservable
import android.databinding.Bindable
import com.google.gson.annotations.SerializedName
import com.zbirka.janez.BR
import com.zbirka.janez.common.ImageSource

class WebData : BaseObservable() {

    @Bindable
    var picture: String = ImageSource.random

    @Bindable
    @SerializedName("userId")
    var userId: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.userId)
        }
        get() = field

    @Bindable
    @SerializedName("id")
    var id: Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.id)
        }
        get() = field

    @Bindable
    @SerializedName("title")
    var title: String = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.title)
        }
        get() = field

    @Bindable
    @SerializedName("body")
    var body: String = String()
        set(value) {
            field = value
            notifyPropertyChanged(BR.body)
        }
        get() = field

}