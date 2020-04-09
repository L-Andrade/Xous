package com.andradel.xous.core.models

import kotlinx.coroutines.CoroutineDispatcher

data class Schedulers(
    private val io: CoroutineDispatcher,
    private val cpu: CoroutineDispatcher,
    private val main: CoroutineDispatcher
)