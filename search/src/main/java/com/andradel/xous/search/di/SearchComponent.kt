package com.andradel.xous.search.di

import com.andradel.xous.scopes.AppScope
import com.andradel.xous.search.ui.BaseSearchFragment
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(scope = AppScope::class)
interface SearchComponent {
    fun inject(fragment: BaseSearchFragment)
}