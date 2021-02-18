package com.andradel.xous.showprofile.di

import com.andradel.xous.scopes.AppScope
import com.andradel.xous.showprofile.ui.ShowProfileFragment
import com.andradel.xous.showprofile.ui.season.SeasonFragment
import com.squareup.anvil.annotations.ContributesTo

@ContributesTo(scope = AppScope::class)
interface ShowProfileComponent {

    fun inject(fragment: ShowProfileFragment)
    fun inject(fragment: SeasonFragment)
}