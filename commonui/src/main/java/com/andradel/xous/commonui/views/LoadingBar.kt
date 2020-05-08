package com.andradel.xous.commonui.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.andradel.xous.commonui.R
import com.andradel.xous.commonui.extensions.animateIn
import com.andradel.xous.commonui.extensions.animateOut
import kotlinx.android.synthetic.main.custom_loading.view.*

class LoadingBar : FrameLayout {

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
        init()
    }

    private fun init() {
        LayoutInflater.from(context).inflate(R.layout.custom_loading, this, true)
    }

    fun hide() {
        progressBar.animateOut()
    }

    fun show() {
        progressBar.animateIn()
    }
}