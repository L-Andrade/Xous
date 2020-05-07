package com.andradel.xous.search.di

import androidx.lifecycle.ViewModel
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.core.di.ViewModelKey
import com.andradel.xous.search.network.SearchAPI
import com.andradel.xous.search.ui.SearchViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
class SearchModule {

    @FeatureScope
    @Provides
    internal fun provideSearchAPI(retrofit: Retrofit): SearchAPI =
        retrofit.create(SearchAPI::class.java)

    @Provides
    @IntoMap
    @ViewModelKey(SearchViewModel::class)
    internal fun provideSearchViewModel(vm: SearchViewModel): ViewModel = vm
}