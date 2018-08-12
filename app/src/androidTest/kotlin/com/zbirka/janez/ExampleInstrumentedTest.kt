package com.zbirka.janez

import android.support.test.runner.AndroidJUnit4
import com.zbirka.janez.application.DaggerZbirkaComponent
import com.zbirka.janez.application.ZbirkaApplicationModule
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import android.support.test.rule.ActivityTestRule
import com.zbirka.janez.application.ZbirkaApplication
import com.zbirka.janez.model.WebData
import com.zbirka.janez.zbirka_app.activity.main.MainActivity
import kotlinx.coroutines.experimental.runBlocking
import java.util.concurrent.CountDownLatch
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getTargetContext()
        assertEquals("com.zbirka.janez", appContext.packageName)
    }

    @Test
    fun useNetwork() = runBlocking {

        val data = DaggerZbirkaComponent.builder()
                .zbirkaApplicationModule(ZbirkaApplicationModule(mActivityTestRule.activity.application as ZbirkaApplication)).build()
                .getDataRepository().getWebData().await()

        assertNotNull(data)
        assertTrue(data.isNotEmpty())
        assertEquals(100, data.size)
    }
}
