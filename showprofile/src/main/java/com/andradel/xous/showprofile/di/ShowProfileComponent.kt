package com.andradel.xous.showprofile.di

import com.andradel.xous.core.di.CoreComponent
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.showprofile.ui.ShowProfileFragment
import dagger.Component

@FeatureScope
@Component(modules = [ShowProfileModule::class], dependencies = [CoreComponent::class])
interface ShowProfileComponent {
    fun inject(showProfileFragment: ShowProfileFragment)
}