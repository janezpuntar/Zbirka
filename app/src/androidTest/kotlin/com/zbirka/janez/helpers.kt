package com.zbirka.janez

import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import android.support.test.InstrumentationRegistry
import android.support.test.InstrumentationRegistry.getInstrumentation
import java.util.concurrent.CountDownLatch

fun rotateScreen(activity: Activity) {
    val countDownLatch = CountDownLatch(1)
    val orientation = InstrumentationRegistry.getTargetContext()
            .resources
            .configuration
            .orientation
    val newOrientation = if (orientation == Configuration.ORIENTATION_PORTRAIT)
        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    else
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

    activity.requestedOrientation = newOrientation

    getInstrumentation().waitForIdle(Runnable { countDownLatch.countDown() })

    try {
        countDownLatch.await()
    } catch (e: InterruptedException) {
        throw RuntimeException("Screen rotation failed", e)
    }

}