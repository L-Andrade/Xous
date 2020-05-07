package com.andradel.xous.core.coroutine

import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.onStart

class BehaviorSubjectChannel<T>(
    initialData: T,
    private val size: Int = 64,
    private val channel: BroadcastChannel<T> = BroadcastChannel(size)
) : BroadcastChannel<T> by channel {
    var value = initialData
        private set

    override fun offer(element: T): Boolean {
        val offered = channel.offer(element)
        if (offered) {
            value = element
        }
        return offered
    }

    override suspend fun send(element: T) {
        channel.send(element)
        value = element
    }

    fun asFlow(): Flow<T> = channel.openSubscription().consumeAsFlow().onStart { emit(value) }
}