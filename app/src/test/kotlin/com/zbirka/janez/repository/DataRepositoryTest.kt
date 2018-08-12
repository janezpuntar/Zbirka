package com.zbirka.janez.repository

import com.zbirka.janez.common.AppCoroutineContext
import com.zbirka.janez.network.DataService
import kotlinx.coroutines.experimental.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations


class DataRepositoryTest {

    @Mock
    lateinit var dataService: DataService

    lateinit var dataRepository: DataRepository

    @Before
    fun setUp() {

        MockitoAnnotations.initMocks(this)
        dataRepository = DataRepository(dataService, AppCoroutineContext())
    }

    @Test
    fun getUserData() = runBlocking {
        assertEquals(" - User data", dataRepository.getUserData().await())
    }

    @Test
    fun getUserDataFailing() = runBlocking {
        val job = dataRepository.getUserDataFailing()

        try {
            job.join()
        } finally {
            val exc = job.getCancellationException()
            assertEquals("exception message", exc.cause!!.message)
        }
    }

    @Test
    fun getBucketData() = runBlocking {
        assertEquals(" - Bucket data", dataRepository.getBucketData().await())
    }

    @Test
    fun getNameData() = runBlocking {
        assertEquals(" - Name data vedro", dataRepository.getNameData("vedro").await())
    }

    @Test
    fun normalFunction() = runBlocking {
        assertEquals(" - Bucket data  - User data  - Name data  - Bucket data  - User data", dataRepository.normalFunction().await())
    }

    @Test
    fun getWebData() {
        runBlocking {
            dataRepository.getWebData()
            verify(dataService).getWebData()
        }
    }
}