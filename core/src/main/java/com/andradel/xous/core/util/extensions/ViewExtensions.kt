package com.andradel.xous.core.util.extensions

import android.view.View
import androidx.annotation.StringRes

fun View.getString(@StringRes id: Int) = this.resources.getString(id)