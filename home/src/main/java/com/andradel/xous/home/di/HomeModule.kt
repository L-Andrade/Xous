package com.andradel.xous.home.di

import androidx.lifecycle.ViewModel
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.core.di.ViewModelKey
import com.andradel.xous.home.network.GeneralAPI
import com.andradel.xous.home.ui.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
class HomeModule {
    @Provides
    @FeatureScope
    internal fun provideGeneralAPI(retrofit: Retrofit): GeneralAPI {
        return retrofit.create(GeneralAPI::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    internal fun provideHomeViewModel(vm: HomeViewModel): ViewModel = vm
}