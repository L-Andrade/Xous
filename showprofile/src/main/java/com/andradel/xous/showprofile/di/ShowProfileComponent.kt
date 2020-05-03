package com.andradel.xous.showprofile.di

import com.andradel.xous.core.di.CoreComponent
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.showprofile.ui.ShowProfileFragment
import com.andradel.xous.showprofile.ui.season.SeasonFragment
import dagger.Component

@FeatureScope
@Component(modules = [ShowProfileModule::class], dependencies = [CoreComponent::class])
interface ShowProfileComponent {

    fun inject(fragment: ShowProfileFragment)

    fun inject(fragment: SeasonFragment)
}