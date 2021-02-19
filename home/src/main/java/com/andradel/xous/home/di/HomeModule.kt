package com.andradel.xous.home.di

import androidx.lifecycle.ViewModel
import com.andradel.xous.core.di.ViewModelKey
import com.andradel.xous.home.network.GeneralAPI
import com.andradel.xous.home.ui.HomeViewModel
import com.andradel.xous.scopes.AppScope
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
@ContributesTo(AppScope::class)
class HomeModule {
    @Provides
    internal fun provideGeneralAPI(retrofit: Retrofit): GeneralAPI {
        return retrofit.create(GeneralAPI::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal fun provideHomeViewModel(vm: HomeViewModel): ViewModel = vm
}