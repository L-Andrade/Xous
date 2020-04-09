package com.andradel.xous.showprofile.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.andradel.xous.common_models.internal.Show
import com.andradel.xous.core.coreComponent
import com.andradel.xous.core.di.ViewModelFactory
import com.andradel.xous.core.util.extensions.observe
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.di.DaggerShowProfileComponent
import kotlinx.android.synthetic.main.show_profile_fragment.*
import javax.inject.Inject

class ShowProfileFragment : Fragment(R.layout.show_profile_fragment) {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: ShowProfileViewModel by viewModels {
        viewModelFactory
    }

    val args: ShowProfileFragmentArgs by navArgs()

    override fun onAttach(context: Context) {
        DaggerShowProfileComponent.builder().coreComponent(coreComponent).build().inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val show = args.show
        setupWithExistingShow(show)
        viewModel.getDetails(show)
        observe(viewModel.details) {

        }

        observe(viewModel.message) {
            showSnackbar(it)
        }
    }

    private fun setupWithExistingShow(show: Show) {
        text.text = show.name
    }
}
