package com.andradel.xous.showprofile.di

import androidx.lifecycle.ViewModel
import com.andradel.xous.core.di.FeatureScope
import com.andradel.xous.core.di.ViewModelKey
import com.andradel.xous.showprofile.network.ShowProfileAPI
import com.andradel.xous.showprofile.ui.ShowProfileViewModel
import com.andradel.xous.showprofile.ui.season.SeasonViewModel
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import retrofit2.Retrofit

@Module
class ShowProfileModule {
    @Provides
    @FeatureScope
    internal fun provideShowProfileAPI(retrofit: Retrofit): ShowProfileAPI {
        return retrofit.create(ShowProfileAPI::class.java)
    }

    @Provides
    @IntoMap
    @ViewModelKey(ShowProfileViewModel::class)
    internal fun provideShowProfileViewModel(vm: ShowProfileViewModel): ViewModel = vm

    @Provides
    @IntoMap
    @ViewModelKey(SeasonViewModel::class)
    internal fun provideSeasonViewModel(vm: SeasonViewModel): ViewModel = vm
}