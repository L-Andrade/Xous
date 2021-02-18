package com.andradel.xous.core

import android.app.Application
import com.andradel.xous.core.di.DaggerCoreComponent
import com.andradel.xous.scopes.ComponentHolder

class XousApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        ComponentHolder.components += DaggerCoreComponent.factory().create(this)
    }
}