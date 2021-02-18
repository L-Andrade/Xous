package com.andradel.xous.home.di

import com.andradel.xous.home.ui.HomeFragment
import com.andradel.xous.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(scope = AppScope::class)
interface HomeComponent {
    fun inject(homeFragment: HomeFragment)
}