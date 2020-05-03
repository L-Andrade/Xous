package com.andradel.xous.core.util.extensions

import android.text.Spanned
import androidx.core.text.HtmlCompat

fun String.toHtmlSpan(): Spanned = HtmlCompat.fromHtml(this, HtmlCompat.FROM_HTML_MODE_LEGACY)