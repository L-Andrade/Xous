package com.andradel.xous.showprofile.ui.gallery

import android.content.ContentValues
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.andradel.xous.commonui.ViewPager2ParallaxPage
import com.andradel.xous.commonui.downloadImage
import com.andradel.xous.commonui.indicator.setViewPagerAndAdapter
import com.andradel.xous.core.util.extensions.showSnackbar
import com.andradel.xous.showprofile.R
import com.andradel.xous.showprofile.ui.gallery.adapter.ImageAdapter
import com.google.android.material.snackbar.BaseTransientBottomBar.LENGTH_LONG
import kotlinx.android.synthetic.main.image_gallery_fragment.*
import java.io.OutputStream

class GalleryFragment : Fragment(R.layout.image_gallery_fragment) {

    private val args: GalleryFragmentArgs by navArgs()
    private val imageAdapter by lazy { ImageAdapter(::askToSaveImage) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val images = args.images.toList()
        val selectedImage = args.selectedImage.split(SEPARATOR).last()

        pager.apply {
            adapter = imageAdapter
            setPageTransformer(ViewPager2ParallaxPage(R.id.photoView))
        }

        indicator.setViewPagerAndAdapter(pager, imageAdapter)

        imageAdapter.submitList(images) {
            val currentIndex = savedInstanceState?.getInt(CURRENT_INDEX)
                ?: images.indexOfFirst { image -> image.split(SEPARATOR).last() == selectedImage }
            pager.setCurrentItem(if (currentIndex > 0) currentIndex else 0, false)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(CURRENT_INDEX, pager.currentItem)
    }

    private fun askToSaveImage(image: String) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            showSnackbar(R.string.want_to_download, actionTitle = R.string.yes) {
                saveImage(image)
            }
        } else {
            showSnackbar(R.string.image_download_unavailable, duration = LENGTH_LONG)
        }
    }

    @RequiresApi(Build.VERSION_CODES.Q)
    private fun saveImage(image: String) = lifecycleScope.launchWhenResumed {
        try {
            val displayName = image.split(SEPARATOR).last()
            val values = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, displayName)
                put(MediaStore.Images.Media.MIME_TYPE, MIME_TYPE)
                put(MediaStore.Images.Media.RELATIVE_PATH, BASE_DIR)
                put(MediaStore.Images.Media.IS_PENDING, 1)
            }

            val collection =
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            val imageUri = requireContext().contentResolver.insert(collection, values)

            if (imageUri == null) {
                onError()
                return@launchWhenResumed
            }

            requireContext().contentResolver.openOutputStream(imageUri).use { out ->
                writeImage(image, out)
            }

            values.clear()
            values.put(MediaStore.Images.Media.IS_PENDING, 0)
            requireContext().contentResolver.update(imageUri, values, null, null)

            onSuccess()
        } catch (e: IllegalStateException) {
            onError()
        }
    }

    private suspend fun writeImage(image: String, outputStream: OutputStream?) {
        if (outputStream == null)
            onError()
        else
            downloadImage(image).compress(Bitmap.CompressFormat.JPEG, MAX_QUALITY, outputStream)
    }

    private fun onError() {
        showSnackbar(R.string.failed_image_download)
    }

    private fun onSuccess() {
        showSnackbar(R.string.successfully_downloaded_image)
    }

    companion object {
        private const val CURRENT_INDEX = "CURRENT_INDEX"
        private const val SEPARATOR = "/"
        private const val MAX_QUALITY = 100
        private const val BASE_DIR = "Pictures/"
        private const val MIME_TYPE = "image/jpeg"
    }
}