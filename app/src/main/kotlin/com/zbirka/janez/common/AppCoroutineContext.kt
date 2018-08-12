package com.zbirka.janez.common

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.android.HandlerContext
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.newFixedThreadPoolContext
import kotlinx.coroutines.experimental.newSingleThreadContext
import kotlin.coroutines.experimental.CoroutineContext

data class AppCoroutineContext(val network: CoroutineContext = newFixedThreadPoolContext(5, "network"),
                               val disc: CoroutineContext = newFixedThreadPoolContext(5, "disc"),
                               val common: CoroutineContext = CommonPool,
                               val database: CoroutineContext = newSingleThreadContext("database"))