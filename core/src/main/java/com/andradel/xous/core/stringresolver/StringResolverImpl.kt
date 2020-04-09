package com.andradel.xous.core.stringresolver

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class StringResolverImpl @Inject constructor(
    private val appContext: Context
) : StringResolver {
    override fun get(@StringRes id: Int): String = appContext.resources.getString(id)
}