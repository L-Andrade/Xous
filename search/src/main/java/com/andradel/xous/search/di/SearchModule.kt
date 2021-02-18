package com.andradel.xous.search.di

import androidx.lifecycle.ViewModel
import com.andradel.xous.core.di.ViewModelKey
import com.andradel.xous.scopes.AppScope
import com.andradel.xous.search.network.SearchAPI
import com.andradel.xous.search.ui.SearchViewModel
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
@ContributesTo(AppScope::class)
class SearchModule {

    @Provides
    internal fun provideSearchAPI(retrofit: Retrofit): SearchAPI =
        retrofit.create(SearchAPI::class.java)

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal fun provideSearchViewModel(vm: SearchViewModel): ViewModel = vm
}