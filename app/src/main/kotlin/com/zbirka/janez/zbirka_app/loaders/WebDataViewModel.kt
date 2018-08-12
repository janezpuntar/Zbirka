package com.zbirka.janez.zbirka_app.loaders

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.zbirka.janez.application.ZbirkaApplication
import com.zbirka.janez.model.WebData
import kotlinx.coroutines.experimental.Job
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class WebDataViewModel: ViewModel() {

    val liveData = MutableLiveData<List<WebData>>()

    private var job: Job = Job()

    init {
        Log.e("WEB", "-- 1 --")
        job = launch(UI) {

            Log.e("WEB", "-- 2 --")
            try {

                liveData.value = ZbirkaApplication.component
                        .getDataRepository().getWebData().await()

                Log.e("WEB", "-- 3 --")
            } catch (e: Throwable) {
                Log.e("WEB", "-- 4 --${e.message}" , e)
            } finally {
                Log.e("WEB", "-- 5 --")
            }
            Log.e("WEB", "-- 6 --")
        }
        Log.e("WEB", "-- 7 --")
    }

//    init {
//
//        val jobww = launch(UI) {
//            try {
//                ZbirkaApplication.component.getDataRepository().normalFunction().await()
//            } catch (e: Exception) {
//                Log.e("fdas", "exc", e)
//            }
//        }
//
//        try {
//            jobww.join()
//        } catch (e: Exception) {
//
//        }
//    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}