package com.andradel.xous.core.models

import kotlinx.coroutines.CoroutineDispatcher

data class Schedulers(
    val io: CoroutineDispatcher,
    val cpu: CoroutineDispatcher,
    val main: CoroutineDispatcher
)