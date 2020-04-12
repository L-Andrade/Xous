package com.andradel.xous.core

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.andradel.xous.core.di.CoreComponent
import com.andradel.xous.core.di.DaggerCoreComponent

class XousApplication : Application() {

    private val coreComponent: CoreComponent by lazy {
        DaggerCoreComponent.builder().application(this).build()
    }

    companion object {
        fun coreComponent(context: Context) =
            (context.applicationContext as XousApplication).coreComponent
    }
}

val Fragment.coreComponent
    get() = XousApplication.coreComponent(requireContext())