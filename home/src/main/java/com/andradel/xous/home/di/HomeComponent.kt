package com.andradel.xous.home.di

import com.andradel.xous.core.di.CoreComponent
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.home.ui.HomeFragment
import dagger.Component

@FeatureScope
@Component(modules = [HomeModule::class], dependencies = [CoreComponent::class])
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
}