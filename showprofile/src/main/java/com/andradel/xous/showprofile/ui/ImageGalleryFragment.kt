package com.andradel.xous.showprofile.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.adapter.BackdropAdapter
import com.andradel.xous.showprofile.ui.adapter.BackdropParallax
import kotlinx.android.synthetic.main.image_gallery_fragment.*

class ImageGalleryFragment : Fragment(R.layout.image_gallery_fragment) {

    private val args: ImageGalleryFragmentArgs by navArgs()
    private val backdropAdapter by lazy { BackdropAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = args.images.toList()
        val selectedImage = args.selectedImage.split(SEPARATOR).last()

        pager.adapter = backdropAdapter
        indicator.setViewPager(pager)
        backdropAdapter.registerAdapterDataObserver(indicator.adapterDataObserver)

        backdropAdapter.submitList(images) {
            val currentIndex = images.indexOfFirst {
                it.split(SEPARATOR).last() == selectedImage
            }
            pager.setCurrentItem(if (currentIndex > 0) currentIndex else 0, false)
        }

        pager.setPageTransformer(BackdropParallax)
    }

    companion object {
        private const val SEPARATOR = "/"
    }
}