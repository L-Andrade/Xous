package com.andradel.xous.search.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.search.R
import com.andradel.xous.search.di.DaggerSearchComponent
import com.andradel.xous.search.ui.state.ViewSearchState
import javax.inject.Inject

abstract class BaseSearchFragment(@LayoutRes layoutId: Int) : Fragment(layoutId) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    protected val viewModel: SearchViewModel by navGraphViewModels(R.id.search) { viewModelFactory }

    override fun onAttach(context: Context) {
        DaggerSearchComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()

        observe(viewModel.viewState) { state ->
            when (state) {
                ViewSearchState.Loading -> onLoading()
                ViewSearchState.Empty -> onEmpty()
                is ViewSearchState.Items -> onItems(state)
            }
        }
    }

    protected abstract fun setupView()

    protected abstract fun onLoading()

    protected abstract fun onEmpty()

    protected abstract fun onItems(state: ViewSearchState.Items)
}