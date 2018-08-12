package com.zbirka.janez.repository

import com.zbirka.janez.common.AppCoroutineContext
import com.zbirka.janez.model.WebData
import com.zbirka.janez.network.DataService
import kotlinx.coroutines.experimental.Deferred
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.delay
import ru.gildor.coroutines.retrofit.awaitResult
import ru.gildor.coroutines.retrofit.getOrThrow

class DataRepository constructor(private val dataService: DataService,
                                 private val appCoroutineContext: AppCoroutineContext) {

    suspend fun getUserDataFailing(): Deferred<String> = async(appCoroutineContext.common) {
        println("getUserData start")
        delay(2000)
        println("getUserData stop")
        throw Exception("exception message")
        " - User data"
    }

    suspend fun getUserData(): Deferred<String> = async(appCoroutineContext.common) {
        println("getUserData start")
        delay(2000)
        println("getUserData stop")
        " - User data"
    }

    suspend fun getBucketData(): Deferred<String> = async(appCoroutineContext.common) {
        println("getBucketData start")
        delay(1000)
        println("getBucketData stop")
        " - Bucket data"
    }

    suspend fun getNameData(bucket: String): Deferred<String> = async(appCoroutineContext.common) {
        println("getNameData start")
        delay(3000)
        println("getNameData stop")
        " - Name data $bucket"
    }

    suspend fun normalFunction(): Deferred<String> = async(appCoroutineContext.common) {

        val result1 = getBucketData()
        val result2 = getUserData()
        val result3 = getNameData(result1.await() + " " + result2.await())

        "${result1.await()} ${result2.await()} ${result3.await()}"
    }

    fun getWebData(): Deferred<List<WebData>> = async(appCoroutineContext.network) {
        dataService.getWebData().awaitResult().getOrThrow()
    }
}