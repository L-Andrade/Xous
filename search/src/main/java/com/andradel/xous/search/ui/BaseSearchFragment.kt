package com.andradel.xous.search.ui

import android.content.Context
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.search.R
import com.andradel.xous.search.di.DaggerSearchComponent
import javax.inject.Inject

abstract class BaseSearchFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel: SearchViewModel by navGraphViewModels(R.id.search) { viewModelFactory }

    override fun onAttach(context: Context) {
        DaggerSearchComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    protected abstract fun setupView()
}