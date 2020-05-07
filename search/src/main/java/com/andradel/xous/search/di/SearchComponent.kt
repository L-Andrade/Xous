package com.andradel.xous.search.di

import com.andradel.xous.core.di.CoreComponent
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.search.ui.BaseSearchFragment
import dagger.Component

@FeatureScope
@Component(modules = [SearchModule::class], dependencies = [CoreComponent::class])
interface SearchComponent {
    fun inject(fragment: BaseSearchFragment)
}